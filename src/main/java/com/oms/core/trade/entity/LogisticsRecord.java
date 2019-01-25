package com.oms.core.trade.entity;

import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LogisticsRecord extends Entity implements Createable, Updateable{

    private String orderId;
    private String expressId;
    private String expressNumber;
    private String detail;
    private Date   createTime;
    private Date   updateTime;
}
