package com.oms.core.product.entity;

import com.wah.doraemon.domain.Createable;
import com.wah.doraemon.domain.Entity;
import com.wah.doraemon.domain.Updateable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Getter
@Setter
public class Item extends Entity implements Createable, Updateable{

    private String  code;
    private String  name;
    private Integer sales;
    private Integer stock;
    private Boolean isCheck;
    private Date    createTime;
    private Date    updateTime;

    private Product product;

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }

        if(object != null && this != null && this.getClass() == object.getClass()){
            Item item = (Item) object;

            if(StringUtils.isNotBlank(this.code) && StringUtils.isNotBlank(item.code)){
                return this.code.equals(item.code);
            }
        }

        return false;
    }
}
