package com.oms.core.trade.service;

import com.oms.core.trade.consts.ExpressType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpressTypeServiceImpl implements ExpressTypeService{

    public List<Map<String, Object>> findAsObjects(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for(ExpressType type : ExpressType.values()){
            Map<String, Object> object = new HashMap<String, Object>();
            object.put("id", type.getId());
            object.put("text", type.getDescription());

            list.add(object);
        }

        return list;
    }
}
