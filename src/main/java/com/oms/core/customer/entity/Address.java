package com.oms.core.customer.entity;

import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Address extends Entity implements Createable, Updateable{

    private String  clientId;
    private Boolean isDefault;
    private String  country;
    private String  province;
    private String  city;
    private String  region;
    private String  detail;
    private String  phone;
    private String  contact;
    private Date    createTime;
    private Date    updateTime;
}
