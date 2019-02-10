package com.oms.core.consts.webservice;

import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.consts.service.ConstService;
import com.wah.doraemon.security.response.Responsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/1.0/const")
public class ConstRestController{

    @Autowired
    private ConstService constService;

    @RequestMapping(value = "/productType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "查询产品类型键值对")
    public Responsed<Map<Object, Object>> findProductType(){
        Map<Object, Object> select = constService.findProductType();

        return new Responsed<Map<Object, Object>>("查询成功", select);
    }

    @RequestMapping(value = "/customized", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "查询产品定制类型键值对")
    public Responsed<Map<Object, Object>> findCustomized(){
        Map<Object, Object> select = constService.findCustomized();

        return new Responsed<Map<Object, Object>>("查询成功", select);
    }

    @RequestMapping(value = "/accountStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "查询账户状态键值对")
    public Responsed<Map<Object, Object>> findAccountStatus(){
        Map<Object, Object> select = constService.findAccountStatus();

        return new Responsed<Map<Object, Object>>("查询成功", select);
    }

    @RequestMapping(value = "/sex", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "查询用户性别键值对")
    public Responsed<Map<Object, Object>> findSex(){
        Map<Object, Object> select = constService.findSex();

        return new Responsed<Map<Object, Object>>("查询成功", select);
    }

    @RequestMapping(value = "/usingStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "查询可用状态键值对")
    public Responsed<Map<Object, Object>> findUsingStatus(){
        Map<Object, Object> select = constService.findUsingStatus();

        return new Responsed<Map<Object, Object>>("查询成功", select);
    }
}
