package com.oms.core.trade.entity;

import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentRecord extends Entity implements Createable, Updateable{

    private String orderId;
    private String paymentId;
    private Double amount;
    private Date   createTime;
    private Date   updateTime;
}
