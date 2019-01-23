package com.oms.core.consts.service;

import com.google.common.collect.Maps;
import com.oms.commons.consts.ProductType;
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
}
