package com.oms.core.lottery.entity;

import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class LotteryRecord extends Entity implements Createable, Updateable{

    private String  lotteryId;
    private String  openId;
    private String  nickname;
    private String  headImgUrl;
    private Boolean isCash;
    private Present present;
    private Date    cashTime;
    private Date    createTime;
    private Date    updateTime;
}
