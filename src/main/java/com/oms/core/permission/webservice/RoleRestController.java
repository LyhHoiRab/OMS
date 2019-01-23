package com.oms.core.permission.webservice;

import com.wah.doraemon.security.response.Responsed;
import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.permission.entity.Role;
import com.oms.core.permission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/role")
public class RoleRestController{

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "新增角色")
    public Responsed save(@RequestBody Role role){
        roleService.save(role);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "更新角色")
    public Responsed update(@RequestBody Role role){
        roleService.update(role);

        return new Responsed("更新成功");
    }

    @RequestMapping(value = "/grant/{roleId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "角色授权")
    public Responsed grand(@PathVariable("roleId") String roleId, @RequestBody List<String> functionIds){
        roleService.grant(roleId, functionIds);

        return new Responsed("授权成功");
    }
}
