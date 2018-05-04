package com.lab.core.trade.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoxProduct{

    private Long id;
    private Long productId;
    private String productCode;
    private String productName;
    private Long prodRetailPrice;
    private Long productNum;
}
