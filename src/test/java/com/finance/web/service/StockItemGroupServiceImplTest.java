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

import java.util.LinkedHashSet;

@SpringBootTest
class StockItemGroupServiceImplTest {

    @Autowired
    InterestGroupService interestGroupService;

    @Autowired
    MongoTemplate mongoTemplate;

    Member member;

    @BeforeEach
    void init() {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("email").is("test@1234.com")));

        member = mongoTemplate.findOne(query, Member.class);
    }

    @Test
    void findInterestGroupsByMemberId() throws Exception {
        //given
        String memberId = member.getId().toString();

        //when
        LinkedHashSet<InterestGroupDto> interestGroups =
                interestGroupService.getInterestGroups(memberId);

        //then
        interestGroups.stream().map(interestGroup -> "interestGroup = " + interestGroup.getName()).forEach(System.out::println);
        interestGroups.stream().map(interestGroup -> "interestGroup = " + interestGroup.getStockItems().size()).forEach(System.out::println);
    }

}