package com.finance.web.domain;

import com.finance.web.vo.StockItem;
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

@SpringBootTest
class StockItemGroupTest {

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
    void create() throws Exception {
        //given

        List<StockItem> stockItemList = new ArrayList<>();

        StockItem one = new StockItem("005930", "삼성전자");
        StockItem two = new StockItem("066570", "LG전자");
        StockItem three = new StockItem("079370", "제우스");
        StockItem four = new StockItem("005930", "SK하이닉스");

        for (StockItem stockItem : Arrays.asList(one, two, three, four)) {
            stockItemList.add(stockItem);
        }

        InterestGroup interestGroup = InterestGroup.builder()
                .name("전자")
                .memberId(member.getId())
                .stockItems(stockItemList)
                .build();
        //when

        mongoTemplate.save(interestGroup);
        //then

    }

}