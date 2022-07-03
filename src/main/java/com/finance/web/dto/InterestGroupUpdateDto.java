package com.finance.web.dto;

import com.finance.web.vo.StockItem;
import lombok.*;

import java.util.List;

import static lombok.AccessLevel.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class InterestGroupUpdateDto {

    private String name;
    private Integer sequence;
    private List<StockItem> stockItems;

}
