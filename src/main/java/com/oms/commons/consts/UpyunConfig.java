package com.oms.commons.consts;

import java.text.SimpleDateFormat;

public class UpyunConfig{

    private static final SimpleDateFormat fomatter = new SimpleDateFormat("yyyyMMdd");

    //账号相关
    public static final String OPERATOR  = "unesmall";
    public static final String BUCKET    = "cloned";
    public static final String PASSWORD  = "unesmall123456";
    public static final String URL       = "https://upyun.ijucaimao.cn";

    //产品证书
    public static final String getCertificateDir(){
        return "/img/certificates/";
    }

    //产品图片
    public static final String getThumbnailDir(){
        return "/img/thumbnail/";
    }
}
