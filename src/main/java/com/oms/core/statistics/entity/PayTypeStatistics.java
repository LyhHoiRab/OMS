package com.oms.core.statistics.entity;

import com.oms.core.profile.entity.Profile;
import com.oms.core.trade.consts.PayType;
import com.oms.core.trade.entity.WechatInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayTypeStatistics{

    //销售ID
    private Long pUserId;
    //销售
    private Profile seller;
    //微信号
    private String wxno;
    //下单微信号
    private WechatInfo wechatInfo;
    //付款类型
    private PayType payType;
    //订单原价
    private Long price;
    //订单实付
    private Long totalFee;
    //订单预付
    private Long prepaidFee;
    //代收金额
    private Long collectFee;

    public Long getPrice(){
        return price == null ? 0 : price / 100;
    }

    public Long getTotalFee(){
        return totalFee == null ? 0 : totalFee / 100;
    }

    public Long getPrepaidFee(){
        return prepaidFee == null ? 0 : prepaidFee / 100;
    }

    public Long getCollectFee(){
        switch(payType){
            case OFFLINE_CASH:
            case OFFLINE_ALIPAY:
            case OFFLINE_WXPAY:
            case OFFLINE_WXQRCODEPAY:
            case OFFLINE_PSBC_PAY:
            case OFFLINE_ABC_PAY:
                return (collectFee = 0L);

            case OFFLINE_ALIPAY_PREPAID:
            case OFFLINE_WXPAY_PREPAID:
            case OFFLINE_WXQRCODEPAY_PREPAID:
            case OFFLINE_PSBC_PREPAID:
            case OFFLINE_ABC_PREPAID:
                return (collectFee = getPrepaidFee() - getPrepaidFee());

            default:
                return (collectFee = 0L);
        }
    }
}
