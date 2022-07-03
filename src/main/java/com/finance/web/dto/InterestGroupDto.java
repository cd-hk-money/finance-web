package com.finance.web.dto;

import com.finance.web.vo.StockItem;
import com.finance.web.domain.InterestGroup;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

import static lombok.AccessLevel.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroupDto {
    private ObjectId id;
    private String name;
    private Integer sequence;
    private List<StockItem> stockItems;
    private String memberId;

    public InterestGroup toDocument() {
        return InterestGroup.builder()
                .name(name)
                .sequence(sequence)
                .stockItems(stockItems)
                .memberId(new ObjectId(memberId))
                .build();
    }
}
