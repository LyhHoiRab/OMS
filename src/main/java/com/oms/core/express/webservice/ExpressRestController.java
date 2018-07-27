package com.oms.core.express.webservice;

import com.oms.core.express.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/express")
public class ExpressRestController{

    @Autowired
    private ExpressService expressService;

    @RequestMapping(value = "/{express}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed update(@RequestBody List<String> ids, @PathVariable("express") Integer express){
        expressService.change(ids, express);

        return new Responsed("修改成功");
    }
}
