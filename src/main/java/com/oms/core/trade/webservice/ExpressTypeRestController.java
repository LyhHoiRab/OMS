package com.oms.core.trade.webservice;

import com.oms.core.trade.service.ExpressTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/1.0/express")
public class ExpressTypeRestController{

    @Autowired
    private ExpressTypeService expressTypeService;

    @RequestMapping(value = "/objects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<Map<String, Object>>> findAsObjects(){
        List<Map<String, Object>> list = expressTypeService.findAsObjects();

        return new Responsed<List<Map<String, Object>>>("查询成功", list);
    }
}
