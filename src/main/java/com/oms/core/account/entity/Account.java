package com.oms.core.account.entity;

import com.oms.commons.consts.AccountStatus;
import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Account extends Entity implements Createable, Updateable{

    private String        username;
    private String        password;
    private AccountStatus status;
    private Date          createTime;
    private Date          updateTime;
}
