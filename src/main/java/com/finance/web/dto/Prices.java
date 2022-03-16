package com.finance.web.dto;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Prices {

    @OneToMany(mappedBy = "stock")
    private List<Price> prices = new ArrayList<>();


}
