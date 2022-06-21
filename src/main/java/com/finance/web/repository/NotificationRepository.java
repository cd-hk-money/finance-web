package com.finance.web.repository;

import com.finance.web.entity.Notification;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, ObjectId> {


}
