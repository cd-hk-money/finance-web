package com.finance.web.repository;

import com.finance.web.domain.Interest;
import com.finance.web.dto.InterestGroupDto;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.LinkedHashSet;

@RequiredArgsConstructor
public class CustomInterestGroupRepositoryImpl implements CustomInterestGroupRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public LinkedHashSet<InterestGroupDto> findByMemberId(ObjectId memberId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id"));
        return null;
    }

    public void addInterest(ObjectId interestGroupId, Interest item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(interestGroupId));

        Update update = new Update();
        update.push("interests", item);

        mongoTemplate.updateFirst(query, update, "interestGroups");
    }

    public void deleteInterest(ObjectId interestGroupId, Interest item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(interestGroupId));

        Update update = new Update();
        update.pull("interests", item);

        mongoTemplate.updateFirst(query, update, "interestGroups");
    }

}
