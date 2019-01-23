package com.oms.commons.consts;

import com.google.gson.annotations.SerializedName;
import com.wah.doraemon.security.exception.UnknownEnumTypeException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ProductType{

    @SerializedName("0")
    ALLERGY_CARE(0, "舒敏"),

    @SerializedName("1")
    FACIAL_MASK(1, "面膜"),

    @SerializedName("2")
    BREAST_ENHANCE(2, "丰胸"),

    @SerializedName("3")
    HAIR_RAISING(3, "育发"),

    @SerializedName("4")
    EYES_CASE(4, "眼部护理"),

    @SerializedName("5")
    GIFT(5, "赠品"),

    @SerializedName("6")
    OTHER(6, "其他");

    private int    id;
    private String description;

    public static ProductType getById(int id){
        for(ProductType type : ProductType.values()){
            if(type.id == id){
                return type;
            }
        }

        throw new UnknownEnumTypeException("未知的定制类型ID[{0}]", id);
    }
}
