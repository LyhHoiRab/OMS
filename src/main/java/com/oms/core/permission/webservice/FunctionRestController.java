package com.oms.core.permission.webservice;

import com.oms.core.permission.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/1.0/function")
public class FunctionRestController{

    @Autowired
    private FunctionService functionService;
}
