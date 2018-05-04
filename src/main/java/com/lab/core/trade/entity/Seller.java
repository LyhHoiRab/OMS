package com.lab.core.trade.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Seller{

    private Long id;
    //销售名称
    private String realName;
    //注册手机
    private String mobile;
    //微信信息
    private List<WechatInfo> wechats;
}
