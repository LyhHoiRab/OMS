package com.lab.core.trade.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum StatusType implements EnumType{

    @SerializedName("4")
    WATI_FOR_SENDING(4, "已付款"),

    @SerializedName("6")
    STOCK_UP(6, "已备货"),

    @SerializedName("5")
    SENT(5, "已发货"),

    @SerializedName("7")
    SIGNED(7, "已签收"),

    @SerializedName("8")
    FAIL(8, "交易失败"),

    @SerializedName("15")
    UNUSUAL(15, "订单异常"),

    @SerializedName("20")
    SUCCESS(20, "交易成功"),

    @SerializedName("999")
    UNKNOWN(999, "未知");

    private int id;
    private String description;

    public static StatusType getById(int id){
        for(StatusType status : StatusType.values()){
            if(status.id == id){
                return status;
            }
        }

        return UNKNOWN;
    }
}
