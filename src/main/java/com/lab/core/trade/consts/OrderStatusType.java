package com.lab.core.trade.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OrderStatusType implements EnumType{

    @SerializedName("0")
    ONWAY(0, "在途中"),

    @SerializedName("1")
    EMBRACE(1, "揽件"),

    @SerializedName("2")
    KNOTTY(2, "疑难"),

    @SerializedName("3")
    SIGN(3, "签收"),

    @SerializedName("4")
    RETURN_SIGN(4, "退签"),

    @SerializedName("5")
    SENDING(5, "派件"),

    @SerializedName("6")
    RETURNING(6, "退回"),

    @SerializedName("999")
    UNKNOWN(999, "未知");

    private int id;
    private String description;

    public static OrderStatusType getById(int id){
        for(OrderStatusType type : OrderStatusType.values()){
            if(type.id == id){
                return type;
            }
        }

        return UNKNOWN;
    }
}
