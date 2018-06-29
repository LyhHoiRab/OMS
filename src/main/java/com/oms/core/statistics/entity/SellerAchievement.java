package com.oms.core.statistics.entity;

import com.oms.core.profile.entity.Profile;
import com.oms.core.trade.entity.WechatInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SellerAchievement{

    //销售ID
    private Long pUserId;
    //销售
    private Profile seller;
    //总销售额
    private Long price;
    //订单数
    private Long total;
    //下单微信
    private List<WechatInfo> wechatInfos;
    //付款类型统计
    private PayTypeStatistics cashStatistics;
    private PayTypeStatistics alipayStatistics;
    private PayTypeStatistics alipayPrepaidStatistics;
    private PayTypeStatistics wxpayStatistics;
    private PayTypeStatistics wxpayPrepaidStatistics;
    private PayTypeStatistics wxqrcodepayStatistics;
    private PayTypeStatistics wxqrcodepayPrepaidStatistics;
    private PayTypeStatistics psbcpayStatistics;
    private PayTypeStatistics psbcpayPrepaidStatistics;
    private PayTypeStatistics abcpayStatistics;
    private PayTypeStatistics abcpayPrepaidStatistics;
    private PayTypeStatistics unknownStatistics;

    public Long getPrice(){
        return price == null ? 0 : price / 100;
    }
}
