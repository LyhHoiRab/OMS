package com.oms.core.customer.entity;

import com.oms.commons.consts.Sex;
import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Client extends Entity implements Createable, Updateable{

    private String accountId;
    private String name;
    private String wxno;
    private Sex    sex;
    private String headImg;
    private String remark;
    private Date   createTime;
    private Date   updateTime;
}
