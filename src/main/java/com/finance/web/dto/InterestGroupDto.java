package com.finance.web.dto;

import com.finance.web.domain.InterestGroup;
import com.finance.web.vo.StockItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroupDto {
    private String _id;
    private String name;
    private Integer sequence;
    private List<StockItem> interests;
    private String memberId;

    public InterestGroup toDocument() {
        return InterestGroup.builder()
                .name(name)
                .sequence(sequence)
                .interests(interests)
                .memberId(new ObjectId(memberId))
                .build();
    }
}
