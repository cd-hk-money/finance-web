package com.finance.web.service;

import com.finance.web.domain.Member;
import com.finance.web.dto.InterestGroupDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;

@SpringBootTest
@Transactional
class InterestGroupServiceTest {

    @Autowired
    InterestGroupService interestGroupService;

    @Autowired
    MongoTemplate mongoTemplate;

    Member member;

    @BeforeEach
    void init() {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("email").is("wkdwoo@admin.com")));

        member = mongoTemplate.findOne(query, Member.class);
    }

    @Test
    void findInterestGroupsByMemberId() throws Exception {
        //given
        String memberId = member.get_id().toString();

        //when
        LinkedHashSet<InterestGroupDto> interestGroups =
                interestGroupService.getInterestGroups(memberId);

        //then
        interestGroups.stream().map(interestGroup -> "interestGroup = " + interestGroup.getName()).forEach(System.out::println);
    }

    @Test
    void getInterestGroup() throws Exception {
        //given
        String memberId = "62b1692848b07a611c0965fe";
        //when
        LinkedHashSet<InterestGroupDto> interestGroups = interestGroupService.getInterestGroups(memberId);

        //then
        for (InterestGroupDto interestGroup : interestGroups) {
            System.out.println("interestGroup = " + interestGroup);
        }

    }

}