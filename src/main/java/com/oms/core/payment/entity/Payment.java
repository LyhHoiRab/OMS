package com.oms.core.payment.entity;

import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import com.wah.doraemon.domain.consts.UsingStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Payment extends Entity implements Createable, Updateable{

    private String      description;
    private String      image;
    private UsingStatus status;
    private Date        createTime;
    private Date        updateTime;
}
