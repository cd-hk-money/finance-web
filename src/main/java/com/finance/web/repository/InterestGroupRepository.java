package com.finance.web.repository;

import com.finance.web.domain.InterestGroup;
import com.finance.web.dto.InterestGroupDto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.LinkedHashSet;

public interface InterestGroupRepository extends MongoRepository<InterestGroup, ObjectId>, CustomInterestGroupRepository {

    LinkedHashSet<InterestGroupDto> findInterestGroupsByMemberId(ObjectId memberId);

    LinkedHashSet<InterestGroupDto> findInterestsGroupsAndInterestsByMemberId(ObjectId memberId);
}
