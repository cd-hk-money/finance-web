package com.finance.web.domain;

import com.finance.web.dto.InterestGroupDto;
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
    private List<Interest> interests;
    private ObjectId memberId;

    public InterestGroupDto toDto() {
        return InterestGroupDto.builder()
                .name(name)
                .sequence(sequence)
                .interests(interests)
                .memberId(String.valueOf(memberId))
                .build();
    }

}
