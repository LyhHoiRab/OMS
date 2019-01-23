package com.oms.core.permission.entity;

import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import com.wah.doraemon.domain.consts.UsingStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Role extends Entity implements Createable, Updateable{

    private String      name;
    private Boolean     isSystem;
    private UsingStatus status;
    private Date        createTime;
    private Date        updateTime;

    private List<Function> functions;
}
