package com.oms.commons.consts;

import com.google.gson.annotations.SerializedName;
import com.wah.doraemon.security.exception.UnknownEnumTypeException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountStatus{

    //正常
    @SerializedName("0")
    NORMAL(0),

    //锁定
    @SerializedName("1")
    LOCKED(1),

    //冻结
    @SerializedName("2")
    FROZEN(2),

    ;

    private int id;

    public static AccountStatus getById(int id){
        for(AccountStatus status : AccountStatus.values()){
            if(status.id == id){
                return status;
            }
        }

        throw new UnknownEnumTypeException("未知的账户状态ID[{0}]", id);
    }
}
