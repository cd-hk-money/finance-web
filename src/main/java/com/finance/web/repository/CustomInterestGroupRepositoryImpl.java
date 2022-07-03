package com.finance.web.repository;

import com.finance.web.vo.StockItem;
import com.finance.web.domain.InterestGroup;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


@RequiredArgsConstructor
public class CustomInterestGroupRepositoryImpl implements CustomInterestGroupRepository {

    private final MongoTemplate mongoTemplate;

    public boolean addInterestToGroup(ObjectId interestGroupId, StockItem item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(interestGroupId));

        Update update = new Update();
        update.push("interests", item);

        return mongoTemplate.updateFirst(query, update, InterestGroup.class).wasAcknowledged();
    }

    @Override
    public boolean updateInterestGroup(ObjectId interestGroupId, InterestGroupUpdateDto updateDto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(interestGroupId));

        Update update = new Update();
        update.set("name", updateDto.getName());
        update.set("sequence", updateDto.getSequence());

        return mongoTemplate.updateFirst(query, update, InterestGroup.class).wasAcknowledged();
    }

    public boolean deleteInterestFromGroup(ObjectId interestGroupId, StockItem item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(interestGroupId));

        Update update = new Update();
        update.pull("interests", item);

        return mongoTemplate.updateFirst(query, update, InterestGroup.class).wasAcknowledged();
    }

    @Override
    public InterestGroupDto updateInterests(ObjectId interestGroupId, List<StockItem> stockItems) {
        InterestGroup interestGroup = mongoTemplate.findOne(
                Query.query(Criteria.where("_id").is(interestGroupId)), InterestGroup.class);

        interestGroup.setStockItems(stockItems);
        InterestGroup save = mongoTemplate.save(interestGroup);

        return save.toDto();
    }
}
