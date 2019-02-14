package com.oms.core.customer.entity;

import com.oms.commons.consts.Sex;
import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Report extends Entity implements Createable, Updateable{

    private String  clientId;
    private String  name;
    private Sex     sex;
    private Integer age;
    private Integer weight;
    private Integer height;
    private String  phone;
    private String  remark;
    private String  image;
    //建议
    private String  proposal;
    //分析
    private String  analysis;
    //问题
    private String  problem;
    private Date    createTime;
    private Date    updateTime;
}
