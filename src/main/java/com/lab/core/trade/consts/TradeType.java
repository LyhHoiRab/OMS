package com.lab.core.trade.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TradeType implements EnumType{

    @SerializedName("12")
    CUSTOMIZE(12, "私人定制"),

    @SerializedName("999")
    UNKNOWN(999, "未知");

    private int id;
    private String description;

    public static TradeType getById(int id){
        for(TradeType type : TradeType.values()){
            if(type.id == id){
                return type;
            }
        }

        return UNKNOWN;
    }
}
