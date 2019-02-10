package com.oms.core.account.entity;

import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import com.oms.commons.consts.Sex;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User extends Entity implements Createable, Updateable{

    private String accountId;
    private String nickname;
    private String headImg;
    private Sex    sex;
    private Date   createTime;
    private Date   updateTime;

    private Account account;
}
