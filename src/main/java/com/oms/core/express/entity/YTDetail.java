package com.oms.core.express.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 圆通Excel模版实体
 */
@Getter
@Setter
public class YTDetail{

    //订单号
    private String  tradeId;
    //商品名称
    private String  productName;
    //数量
    private Integer count;
    //收货人名称
    private String  contactName;
    //收货人省
    private String  province;
    //收货人城市
    private String  city;
    //收货人地区
    private String  district;
    //收货人地址
    private String  address;
    //收货人电话
    private String  phone;
    //发货人名称
    private String  senderName;
    //发货人电话
    private String  senderPhone;
    //发货人省市
    private String  senderProvince;
    //发货人城市
    private String  senderCity;
    //发货人地区
    private String  senderDistrict;
    //发货人地址
    private String  senderAddress;
    //发货人邮编
    private String  senderZip;
    //价格
    private Double  price;
    //代收
    private Double  collect;
    //备注
    private String  remark;
    //买家邮编
    private String  zip;
    //保价金额
    private Double  protection;
}
