package com.finance.web.repository;

import com.finance.web.entity.Member;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    private final MongoTemplate mongoTemplate;


    @Override
    public void pushItemToNotifications(ObjectId memberId, String item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(memberId));

        Update update = new Update();
        update.push("notifications", item);

        mongoTemplate.updateFirst(query, update, "members");
    }

    @Override
    public void deleteItemFromNotifications(ObjectId memberId, String item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(memberId));

        Update update = new Update();
        update.pull("notifications", item);

        mongoTemplate.updateFirst(query, update, "members");
    }
}
