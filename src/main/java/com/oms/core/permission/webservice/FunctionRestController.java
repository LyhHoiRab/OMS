package com.oms.core.permission.webservice;

import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.permission.entity.Function;
import com.oms.core.permission.service.FunctionService;
import com.wah.doraemon.security.response.Responsed;
import com.wah.doraemon.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/1.0/function")
public class FunctionRestController{

    @Autowired
    private FunctionService functionService;

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "分页查询功能信息")
    public Responsed<Page<Function>> page(Long pageNum, Long pageSize, String api, String method, Boolean allocatable, Boolean granted, Boolean cookie){
        Page<Function> page = functionService.page(pageNum, pageSize, api, method, allocatable, granted, cookie);

        return new Responsed<Page<Function>>("查询成功", page);
    }
}
