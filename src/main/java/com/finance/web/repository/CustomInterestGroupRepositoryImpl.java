package com.finance.web.repository;

import com.finance.web.entity.Interest;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@RequiredArgsConstructor
public class CustomInterestGroupRepositoryImpl implements CustomInterestGroupRepository {

    private final MongoTemplate mongoTemplate;

    public void addInterest(ObjectId interestGroupId, Interest item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(interestGroupId));

        Update update = new Update();
        update.push("interests", item);

        mongoTemplate.updateFirst(query, update, "interestGroups");
    }

}
