package com.oms.core.product.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * table: nine_lab_pitem
 */
@Getter
@Setter
public class Item{

    private Long id;
    private String itemCode;
    private String itemName;
    private String itemSpec;
    private String unit;
    //库存
    private Integer num;
    //销售量
    private Integer sellNum;
    //创建时间
    private Date dateCreated;
    //修改时间
    private Date lastUpdated;
    //是否审核
    private Boolean isCheck;
}
