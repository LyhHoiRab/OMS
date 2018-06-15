package com.oms.core.trade.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * table: logistics
 */
@Getter
@Setter
public class Logistics{

    private Long   id;
    //买家ID
    private Long   userId;
    //订单号
    private String tradeId;
    //收货人
    private String contactName;
    //联系电话
    private String phone;
    //联系电话
    private String mobile;
    //国家
    private String country;
    //省
    private String province;
    //市
    private String city;
    //区
    private String district;
    //详细地址
    private String addrDetail;
    //全地址
    private String addr;
    //备注
    private String memo;
    //创建时间
    private Date   dateCreated;
    //更新时间
    private Date   lastUpdated;
}
