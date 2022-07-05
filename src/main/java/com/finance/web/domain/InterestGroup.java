package com.finance.web.domain;

import com.finance.web.dto.InterestGroupDto;
import com.finance.web.vo.StockItem;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.util.List;

import static lombok.AccessLevel.*;

@Getter
@Builder
@Document(collection = "interestGroups")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroup {

    @Id
    private ObjectId _id;

    private String name;
    private Integer sequence;

    @Field("interests")
    private List<StockItem> interests;

    private ObjectId memberId;

    public void setStockItems(List<StockItem> stockItems) {
        this.interests = stockItems;
    }

    public InterestGroupDto toDto() {
        return InterestGroupDto.builder()
                ._id(_id.toString())
                .name(name)
                .sequence(sequence)
                .interests(interests)
                .memberId(String.valueOf(memberId))
                .build();
    }

}
