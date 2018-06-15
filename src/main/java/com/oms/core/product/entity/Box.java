package com.oms.core.product.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * table: miku_mine_sc_box
 */
@Getter
@Setter
public class Box{

    private Long id;
    private Long version;
    private Date dateCreated;
    private Date lastUpdate;
}
