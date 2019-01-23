package com.oms.core.permission.webservice;

import com.wah.doraemon.security.response.Responsed;
import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.permission.entity.Menu;
import com.oms.core.permission.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/1.0/menu")
public class MenuRestController{

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "新增菜单")
    public Responsed save(@RequestBody Menu menu){
        menuService.save(menu);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "更新菜单")
    public Responsed update(@RequestBody Menu menu){
        menuService.update(menu);

        return new Responsed("更新成功");
    }
}
