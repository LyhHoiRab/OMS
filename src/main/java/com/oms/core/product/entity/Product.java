package com.oms.core.product.entity;

import com.oms.commons.consts.Customized;
import com.oms.commons.consts.ProductType;
import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Product extends Entity implements Createable, Updateable{

    //编码
    private String      code;
    //编号
    private String      number;
    //名称
    private String      name;
    //定制类型
    private Customized  customized;
    //产品类型
    private ProductType type;
    //质地
    private String      texture;
    //规格
    private String      specification;
    //售价
    private Double      price;
    //说明
    private String      description;
    //功效
    private String      effect;
    //药监局网址
    private String      sfda;
    //备案证书
    private String      certificates;
    //图片
    private String      thumbnail;
    private Date        createTime;
    private Date        updateTime;
}
