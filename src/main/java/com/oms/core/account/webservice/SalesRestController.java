package com.oms.core.account.webservice;

import com.oms.commons.consts.AccountStatus;
import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.account.entity.User;
import com.oms.core.account.service.SalesService;
import com.wah.doraemon.security.response.Responsed;
import com.wah.doraemon.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/sales")
public class SalesRestController{

    @Autowired
    private SalesService salesService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "添加销售信息")
    public Responsed save(@RequestBody User sales){
        salesService.save(sales);

        return new Responsed("添加成功");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "修改销售信息")
    public Responsed update(@RequestBody User sales){
        salesService.update(sales);

        return new Responsed("修改成功");
    }

    @RequestMapping(value = "/status", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "批量修改账户状态")
    public Responsed updateStatus(@RequestBody List<String> accountIds, AccountStatus status){
       salesService.updateStatus(accountIds, status);

       return new Responsed("修改成功");
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "根据账户ID查询销售信息")
    public Responsed<User> getByAccountId(@PathVariable("accountId") String accountId){
        User user = salesService.getByAccountId(accountId);

        return new Responsed<User>("查询成功", user);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "分页查询销售信息")
    public Responsed<Page<User>> page(Long pageNum, Long pageSize, String username, String nickname, AccountStatus status){
        Page<User> page = salesService.page(pageNum, pageSize, username, nickname, status);

        return new Responsed<Page<User>>("查询成功", page);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "查询销售信息")
    public Responsed<List<User>> find(String username, String nickname, AccountStatus status){
        List<User> list = salesService.find(username, nickname, status);

        return new Responsed<List<User>>("查询成功", list);
    }
}
