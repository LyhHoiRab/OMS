package com.oms.core.popularize.entity;

import com.oms.core.account.entity.User;
import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WxInfo extends Entity implements Createable, Updateable{

    private String accountId;
    private String wxno;
    private String nickname;
    private String phone;
    private String remark;
    private String image;
    private String qr;
    private Date   createTime;
    private Date   updateTime;

    //绑定的销售
    private User sales;
}
