package com.oms.commons.consts;

public class CacheName {

    //keys
    public static final String RSA_PRIVATE_KEY = "key:rsa:private";
    public static final String RSA_PUBLIC_KEY  = "key:rsa:public";

    //账户cookie
    public static final String ACCOUNT_COOKIE          = "account:cookie:";
    //账户角色
    public static final String ACCOUNT_ROLE            = "account:role:";
    //账户session
    public static final String ACCOUNT_SESSION         = "account:session";
    //账户session时效
    public static final String ACCOUNT_SESSION_EXPIRES = "account:session:expires:";

    //角色权限
    public static final String ROLE_FUNCTION = "role:function:";

    //cookie有效时间
    public static final int    COOKIE_TIME_OUT = 7200;

    //权限列表
    public static final String PERMISSION_NEED_NOT_COOKIE  = "permission:need_not:cookie";
    public static final String PERMISSION_NEED_NOT_GRANTED = "permission:need_not:grant";
}
