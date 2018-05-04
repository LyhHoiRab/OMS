package com.lab.core.trade.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WechatInfo{

    private Long id;
    //销售
    private Seller seller;
    //类型
    private String description;
    //微信名
    private String wxName;
    //微信号
    private String wxNo;
    //微信二维码
    private String wxQrcodeUrl;
    //头像
    private String wxPicUrl;
    //绑定手机
    private String mobile;
}
