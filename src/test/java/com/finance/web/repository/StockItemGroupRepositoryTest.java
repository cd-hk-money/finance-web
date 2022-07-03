package com.finance.web.repository;

import com.finance.web.vo.StockItem;
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
class StockItemGroupRepositoryTest {

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
                .stockItems(new ArrayList<>())
                .build();

        //when
        interestGroupRepository.save(interestGroup);

        //then

    }


    @Test
    void createInterestGroup() throws Exception {
        //given

        List<StockItem> stockItemList = new ArrayList<>();

        StockItem one = new StockItem("005930", "삼성중공업");
        StockItem two = new StockItem("066570", "두산중공업");
        StockItem three = new StockItem("079370", "현대중공업");

        for (StockItem stockItem : Arrays.asList(one, two, three)) {
            stockItemList.add(stockItem);
        }

        InterestGroup interestGroup = InterestGroup.builder()
                .name("중공업")
                .memberId(member.getId())
                .stockItems(stockItemList)
                .build();
        //when

        interestGroupRepository.save(interestGroup);
        //then

    }

    @Test
    void addInterestToGroup() throws Exception {
        //given
        //Document document = new Document()

        StockItem item = new StockItem("7654321", "x테스트x");
        ObjectId objectId = new ObjectId("62b1759d92353f579c9844db");

        //when
        interestGroupRepository.addInterestToGroup(objectId, item);

        //then
        Optional<InterestGroup> byId = interestGroupRepository.findById(objectId);
        if (byId.isPresent()) {
            List<StockItem> stockItems = byId.get().getStockItems();
            stockItems.stream().map(interest -> "interest.getStockCode() = "
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
            System.out.println("interestGroup.getInterests() = " + interestGroup.getStockItems());
        }
        //then
    }


    @Test
    void deleteInterestFromGroup() throws Exception {
        //given
        StockItem item = new StockItem("123456", "테스트테스트");
        ObjectId objectId = new ObjectId("62b1759d92353f579c9844db");

        //when
        interestGroupRepository.deleteInterestFromGroup(objectId, item);

        //then
        Optional<InterestGroup> byId = interestGroupRepository.findById(objectId);
        if (byId.isPresent()) {
            List<StockItem> stockItems = byId.get().getStockItems();
            stockItems.stream().map(interest -> "interest.getStockCode() = "
                    + interest.getStockCode()).forEach(System.out::println);
        }
    }


}