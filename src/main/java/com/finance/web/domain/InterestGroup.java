package com.finance.web.domain;

import com.finance.web.dto.InterestGroupDto;
import com.finance.web.vo.StockItem;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static lombok.AccessLevel.*;

@Getter
@Builder
@Document(collection = "interestGroups")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroup {

    @Id
    private ObjectId id;

    private String name;
    private Integer sequence;
    private List<StockItem> stockItems;
    private ObjectId memberId;

    public void setStockItems(List<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    public InterestGroupDto toDto() {
        return InterestGroupDto.builder()
                .name(name)
                .sequence(sequence)
                .stockItems(stockItems)
                .memberId(String.valueOf(memberId))
                .build();
    }

}
