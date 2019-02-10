package com.oms.core.consts.service;

import com.google.common.collect.Maps;
import com.oms.commons.consts.AccountStatus;
import com.oms.commons.consts.Customized;
import com.oms.commons.consts.ProductType;
import com.oms.commons.consts.Sex;
import com.wah.doraemon.domain.consts.UsingStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
public class ConstServiceImpl implements ConstService{

    @Override
    public Map<Object, Object> findProductType(){
        Map<Object, Object> select = Maps.newHashMap();

        for(ProductType type : ProductType.values()){
            select.put(type.getId(), type.getDescription());
        }

        return select;
    }

    @Override
    public Map<Object, Object> findCustomized(){
        Map<Object, Object> select = Maps.newHashMap();

        for(Customized customized : Customized.values()){
            select.put(customized.getId(), customized.getDescription());
        }

        return select;
    }

    @Override
    public Map<Object, Object> findAccountStatus(){
        Map<Object, Object> select = Maps.newHashMap();

        for(AccountStatus status : AccountStatus.values()){
            select.put(status.getId(), status.getDescription());
        }

        return select;
    }

    @Override
    public Map<Object, Object> findSex(){
        Map<Object, Object> select = Maps.newHashMap();

        for(Sex sex : Sex.values()){
            select.put(sex.getId(), sex.getDescription());
        }

        return select;
    }

    @Override
    public Map<Object, Object> findUsingStatus(){
        Map<Object, Object> select = Maps.newHashMap();

        for(UsingStatus status : UsingStatus.values()){
            select.put(status.getId(), status.getDescription());
        }

        return select;
    }
}
