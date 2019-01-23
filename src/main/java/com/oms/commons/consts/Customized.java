package com.oms.commons.consts;

import com.google.gson.annotations.SerializedName;
import com.wah.doraemon.security.exception.UnknownEnumTypeException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Customized{

    @SerializedName("0")
    SKIN_CARE(0, "护肤定制"),

    @SerializedName("1")
    PRIVACY_CARE(1, "私密护理"),

    @SerializedName("2")
    KEEP_FIT(2, "减肥定制"),

    @SerializedName("3")
    ALOPECIA_CASE(3, "脱发定制");

    private int    id;
    private String description;

    public static Customized getById(int id){
        for(Customized customized : Customized.values()){
            if(customized.id == id){
                return customized;
            }
        }

        throw new UnknownEnumTypeException("未知的定制类型ID[{0}]", id);
    }
}
