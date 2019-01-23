package com.oms.core.permission.entity;

import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Menu extends Entity implements Createable, Updateable{

    private String  functionId;
    private String  name;
    private String  routerPath;
    private String  icon;
    private Boolean allocatable;
    private Date    createTime;
    private Date    updateTime;
}
