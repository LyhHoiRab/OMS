package com.oms.core.trade.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExpressType implements EnumType{

    @SerializedName("1")
    SHUNFENG(1, "顺丰"),

    @SerializedName("2")
    YUANTONG(2, "圆通"),

    @SerializedName("3")
    DEBANG(3, "德邦"),

    @SerializedName("4")
    EMS(4, "邮政"),

    @SerializedName("5")
    JD(5, "京东快递"),

    @SerializedName("6")
    SHENTONG(6, "申通"),

    @SerializedName("7")
    ZHONGTONG(7, "中通"),

    @SerializedName("8")
    YUNDA(8, "韵达"),

    @SerializedName("999")
    UNKNOWN(999, "未知");

    private int id;
    private String description;

    public static ExpressType getById(int id){
        for(ExpressType type : ExpressType.values()){
            if(type.id == id){
                return type;
            }
        }

        return UNKNOWN;
    }
}
