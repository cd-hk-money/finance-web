package com.finance.web.repository;

import com.finance.web.domain.Interest;
import com.finance.web.domain.InterestGroup;
import com.finance.web.domain.Member;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class InterestGroupRepositoryTest {

    @Autowired
    InterestGroupRepository interestGroupRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    Member member;

    @BeforeEach
    void init() {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("email").is("test@1234.com")
        ));

        member = mongoTemplate.findOne(query, Member.class);
    }

    @Test
    void createOnlyInterestGroup() throws Exception {
        //given
        InterestGroup interestGroup = InterestGroup.builder()
                .name("건설업")
                .memberId(member.getId())
                .interests(new ArrayList<>())
                .build();

        //when
        interestGroupRepository.save(interestGroup);

        //then

    }


    @Test
    void createInterestGroup() throws Exception {
        //given

        List<Interest> interestList = new ArrayList<>();

        Interest one = new Interest("005930", "삼성중공업");
        Interest two = new Interest("066570", "두산중공업");
        Interest three = new Interest("079370", "현대중공업");

        for (Interest interest : Arrays.asList(one, two, three)) {
            interestList.add(interest);
        }

        InterestGroup interestGroup = InterestGroup.builder()
                .name("중공업")
                .memberId(member.getId())
                .interests(interestList)
                .build();
        //when

        interestGroupRepository.save(interestGroup);
        //then

    }

    @Test
    void addInterestToGroup() throws Exception {
        //given
        //Document document = new Document()

        Interest item = new Interest("7654321", "x테스트x");
        ObjectId objectId = new ObjectId("62b1759d92353f579c9844db");

        //when
        interestGroupRepository.addInterest(objectId, item);

        //then
        Optional<InterestGroup> byId = interestGroupRepository.findById(objectId);
        if (byId.isPresent()) {
            List<Interest> interests = byId.get().getInterests();
            interests.stream().map(interest -> "interest.getStockCode() = "
                    + interest.getStockCode()).forEach(System.out::println);
        }
    }


    @Test
    void findInterestGroupByGroupId() throws Exception {
        //given
        ObjectId objectId = new ObjectId("62b1759d92353f579c9844db");
        Optional<InterestGroup> byId = interestGroupRepository.findById(objectId);
        //when

        if (byId.isPresent()) {
            InterestGroup interestGroup = byId.get();
            System.out.println("interestGroup.interestGroup.getId() = " + interestGroup.getId());
            System.out.println("interestGroup.getName() = " + interestGroup.getName());
        }
        //then

    }

    @Test
    void findInterestGroupByUserId() throws Exception {
        //given
        ObjectId objectId = member.getId();
        Optional<InterestGroup> byId = interestGroupRepository.findById(objectId);
        //when

        if (byId.isPresent()) {
            InterestGroup interestGroup = byId.get();
            System.out.println("interestGroup = " + interestGroup);
            System.out.println("interestGroup.getInterests() = " + interestGroup.getInterests());
        }
        //then
    }


    @Test
    void deleteInterestFromGroup() throws Exception {
        //given
        Interest item = new Interest("123456", "테스트테스트");
        ObjectId objectId = new ObjectId("62b1759d92353f579c9844db");

        //when
        interestGroupRepository.deleteInterest(objectId, item);

        //then
        Optional<InterestGroup> byId = interestGroupRepository.findById(objectId);
        if (byId.isPresent()) {
            List<Interest> interests = byId.get().getInterests();
            interests.stream().map(interest -> "interest.getStockCode() = "
                    + interest.getStockCode()).forEach(System.out::println);
        }
    }


}