package com.oms.core.lottery.entity;

import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class Present extends Entity implements Createable, Updateable{

    private String  lotteryId;
    private String  description;
    private Double  probability;
    private Integer appearByDay;
    private Integer numberByTime;
    private Integer serial;
    private Date    createTime;
    private Date    updateTime;
}
