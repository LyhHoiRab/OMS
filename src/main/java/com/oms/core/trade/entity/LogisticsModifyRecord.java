package com.oms.core.trade.entity;

import com.oms.core.trade.consts.ExpressType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * table: logistics_modifi_logs
 */
@Getter
@Setter
public class LogisticsModifyRecord{

    private Long id;
    //订单ID
    private String tradeId;
    //操作员
    private String operator;
    //操作时间
    private Date operateTime;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //原快递类型
    private ExpressType originalKdType;
    //修改快递类型
    private ExpressType modifiKdType;
    //原物流号
    private String originalWlnumber;
    //修改物流号
    private String modifiWlnumber;
    //原国家
    private String originalCountry;
    //修改国家
    private String modifiCountry;
    //原省份
    private String originalProvince;
    //修改省份
    private String modifiProvince;
    //原城市
    private String originalCity;
    //修改城市
    private String modifiCity;
    //原街道/区
    private String originalDistrict;
    //修改街道/区
    private String modifiDistrict;
    //原地址
    private String originalAddrDetail;
    //修改地址
    private String modifiAddrDetail;
    //原收件人
    private String originalContactName;
    //修改收件人
    private String modifiContactName;
    //原联系电话
    private String originalPhone;
    //修改联系电话
    private String modifiPhone;
}
