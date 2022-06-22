package com.finance.web.dto;

import com.finance.web.domain.Interest;
import com.finance.web.domain.InterestGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroupSaveDto {

    private String name;
    private Integer sequence;
    private List<Interest> interests;
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
