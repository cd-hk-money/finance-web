package com.finance.web.dto;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.HashSet;

import static lombok.AccessLevel.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroupResponseDto {

    private ObjectId id;
    private String name;
    private HashSet<InterestResponseDto> item;

}
