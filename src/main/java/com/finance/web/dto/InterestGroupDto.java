package com.finance.web.dto;

import com.finance.web.domain.Interest;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

import static lombok.AccessLevel.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroupDto {
    private ObjectId id;
    private String name;
    private Integer sequence;
    private List<Interest> interests;
}
