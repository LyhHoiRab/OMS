package com.lab.core.trade.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OrderStateType implements EnumType{

    @SerializedName("0")
    NO_RESULT(0, "物流单暂无结果"),

    @SerializedName("1")
    SUCCESS(1, "查询成功"),

    @SerializedName("2")
    ERROR(2, "接口出现异常"),

    @SerializedName("999")
    UNKNOWN(999, "未知");

    private int id;
    private String description;

    public static OrderStateType getById(int id){
        for(OrderStateType type : OrderStateType.values()){
            if(type.id == id){
                return type;
            }
        }

        return UNKNOWN;
    }
}
