package com.oms.core.permission.entity;

import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Getter
@Setter
public class Function extends Entity implements Createable, Updateable{

    private String  api;
    private String  method;
    private String  description;
    //可分配
    private Boolean allocatable;
    //需权限
    private Boolean granted;
    //需登录
    private Boolean cookie;
    private Date    createTime;
    private Date    updateTime;

    public boolean equals(Object object){
        if(this == object){
            return true;
        }

        if(object != null && this.getClass() == object.getClass()){
            Function entity = (Function) object;

            if(StringUtils.isNotBlank(this.api)
                && StringUtils.isNotBlank(entity.api)
                && StringUtils.isNotBlank(this.method)
                && StringUtils.isNotBlank(entity.method)){

                return this.api.equals(entity.api) && this.method.equals(entity.method);
            }
        }

        return false;
    }
}
