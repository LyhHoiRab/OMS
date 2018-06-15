package com.oms.core.trade.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PayType implements EnumType{

    @SerializedName("6")
    OFFLINE_CASH(6, "线下现金/货到付款"),

    @SerializedName("7")
    OFFLINE_ALIPAY(7, "线下支付宝"),

    @SerializedName("8")
    OFFLINE_WXPAY(8, "线下微信"),

    @SerializedName("9")
    OFFLINE_ALIPAY_PREPAID(9, "线下支付宝支付预付款"),

    @SerializedName("10")
    OFFLINE_WXPAY_PREPAID(10, "线下微信支付预付款"),

    @SerializedName("11")
    OFFLINE_WXQRCODEPAY(11, "线下微信二维码支付"),

    @SerializedName("12")
    OFFLINE_WXQRCODEPAY_PREPAID(12, "线下微信二维码支付预付款"),

    @SerializedName("13")
    OFFLINE_PSBC_PAY(13, "线下邮政储蓄银行支付"),

    @SerializedName("14")
    OFFLINE_PSBC_PREPAID(14, "线下邮政储蓄银行预支付"),

    @SerializedName("15")
    OFFLINE_ABC_PAY(15, "线下农业银行支付"),

    @SerializedName("16")
    OFFLINE_ABC_PREPAID(16, "线下农业银行预支付"),

    @SerializedName("999")
    UNKNOWN(999, "未知");

    private int id;
    private String description;

    public static PayType getById(int id){
        for(PayType pay : PayType.values()){
            if(pay.id == id){
                return pay;
            }
        }

        return UNKNOWN;
    }
}
