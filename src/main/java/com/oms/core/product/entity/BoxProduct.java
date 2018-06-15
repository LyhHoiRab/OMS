package com.oms.core.product.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * table: miku_mine_box_product
 */
@Getter
@Setter
public class BoxProduct{

    private Long    id;
    private Long    version;
    private Product product;
    private Long    boxId;
    private Long    prodPrice;
    private Integer num;
    private Date    dateCreated;
    private Date    lastUpdated;
}
