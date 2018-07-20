package com.oms.core.statistics.entity;

import com.oms.core.profile.entity.Profile;
import com.oms.core.trade.entity.WechatInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WechatAchievement{

    //销售ID
    private Long pUserId;
    //销售
    private Profile seller;
    //总销售额
    private Long price;
    //订单数
    private Long total;
    //先单微信号
    private String wxNo;
    //下单微信
    private WechatInfo wechatInfo;
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

    public Long getWxpayPrice(){
        Long wxpay        = wxpayStatistics        == null ? 0 : wxpayStatistics.getPrice();
        Long wxpayPrepaid = wxpayPrepaidStatistics == null ? 0 : wxpayPrepaidStatistics.getPrepaidFee();

        return (wxpay + wxpayPrepaid);
    }

    public Long getAlipayPrice(){
        Long alipay        = alipayStatistics        == null ? 0 : alipayStatistics.getPrice();
        Long alipayPrepaid = alipayPrepaidStatistics == null ? 0 : alipayPrepaidStatistics.getPrepaidFee();

        return (alipay + alipayPrepaid);
    }

    public Long getWxqrcodepayPrice(){
        Long wxqrcodepay        = wxqrcodepayStatistics        == null ? 0 : wxqrcodepayStatistics.getPrice();
        Long wxqrcodepayPrepaid = wxqrcodepayPrepaidStatistics == null ? 0 : wxqrcodepayPrepaidStatistics.getPrepaidFee();

        return (wxqrcodepay + wxqrcodepayPrepaid);
    }

    public Long getPsbcpayPrice(){
        Long psbcpay        = psbcpayStatistics        == null ? 0 : psbcpayStatistics.getPrice();
        Long psbcpayPrepaid = psbcpayPrepaidStatistics == null ? 0 : psbcpayPrepaidStatistics.getPrepaidFee();

        return (psbcpay + psbcpayPrepaid);
    }

    public Long getAbcpayPrice(){
        Long abcpay        = abcpayStatistics        == null ? 0 : abcpayStatistics.getPrice();
        Long abcpayPrepaid = abcpayPrepaidStatistics == null ? 0 : abcpayPrepaidStatistics.getPrepaidFee();

        return (abcpay + abcpayPrepaid);
    }

    public Long getCollectPrice(){
        Long wxpayCollect    = wxpayPrepaidStatistics       == null ? 0 : wxpayPrepaidStatistics.getCollectFee();
        Long wxqrcodeCollect = wxqrcodepayPrepaidStatistics == null ? 0 : wxqrcodepayPrepaidStatistics.getCollectFee();
        Long alipayCollect   = alipayPrepaidStatistics      == null ? 0 : alipayPrepaidStatistics.getCollectFee();
        Long psbcpayCollect  = psbcpayPrepaidStatistics     == null ? 0 : psbcpayPrepaidStatistics.getCollectFee();
        Long abcpayCollect   = abcpayPrepaidStatistics      == null ? 0 : abcpayPrepaidStatistics.getCollectFee();
        Long cashCollect     = cashStatistics               == null ? 0 : cashStatistics.getCollectFee();

        return (wxpayCollect + wxqrcodeCollect + alipayCollect + psbcpayCollect + abcpayCollect + cashCollect);
    }
}
