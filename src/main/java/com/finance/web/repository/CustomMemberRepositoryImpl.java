package com.finance.web.repository;

import com.finance.web.domain.Member;
import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    private final MongoTemplate mongoTemplate;


    public List<Member> findMembersFollowingStockItem(StockItem stockItem) {
        Criteria criteria = new Criteria().andOperator(
                Criteria.where("_class").is("com.finance.web.entity.Member"),
                Criteria.where("notifications.stockCode").is(stockItem.getStockCode()),
                Criteria.where("notifications.stockName").is(stockItem.getStockName())
        );
        return mongoTemplate.find(new Query(criteria), Member.class);
    }

    @Override
    public boolean pushMessage(StockItem stockItem, Message message) {
        Criteria criteria = new Criteria().andOperator(
                Criteria.where("_class").is("com.finance.web.entity.Member"),
                Criteria.where("notifications.stockCode").is(stockItem.getStockCode()),
                Criteria.where("notifications.stockName").is(stockItem.getStockName()));

        Update update = new Update();
        update.push("messages", message);
        return mongoTemplate.updateMulti(new Query(criteria), update, "members").wasAcknowledged();
    }


    @Override
    public boolean pushItemToNotifications(ObjectId memberId, StockItem item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(memberId));

        Update update = new Update();
        update.push("notifications", item);

        return mongoTemplate.updateFirst(query, update, "members").wasAcknowledged();
    }

    @Override
    public HashSet<StockItem> findNotificationsByMemberId(ObjectId memberId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(memberId));

        return mongoTemplate.findById(memberId, Member.class).getNotifications();
    }

    @Override
    public boolean deleteItemFromNotifications(ObjectId memberId, StockItem item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(memberId));

        Update update = new Update();
        update.pull("notifications", item);

        return mongoTemplate.updateFirst(query, update, "members").wasAcknowledged();
    }

    @Override
    public HashSet<StockItem> findItemInNotifications(ObjectId memberId) {
        return null;
    }
}
