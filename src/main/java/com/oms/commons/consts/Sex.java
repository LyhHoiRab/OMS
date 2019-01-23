package com.oms.commons.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Sex{

    @SerializedName("0")
    FEMALE(0, "女性"),

    @SerializedName("1")
    MALE(1, "男性"),

    @SerializedName("2")
    UNKNOWN(2, "未知");

    private int    id;
    private String description;

    public static Sex getById(int id){
        for(Sex sex : Sex.values()){
            if(sex.id == id){
                return sex;
            }
        }

        return UNKNOWN;
    }
}
