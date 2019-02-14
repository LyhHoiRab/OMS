package com.oms.core.customer.webservice;

import com.oms.commons.consts.CacheName;
import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.customer.entity.Client;
import com.oms.core.customer.service.ClientService;
import com.wah.doraemon.security.response.Responsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/client")
public class ClientRestController{

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "添加客户信息")
    public Responsed save(HttpServletRequest request, @RequestBody Client client){
        String accountId = (String) request.getSession().getAttribute(CacheName.ACCOUNT_COOKIE);

        client.setAccountId(accountId);
        clientService.save(client);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "修改客户信息")
    public Responsed update(@RequestBody Client client){
        clientService.update(client);

        return new Responsed("修改成功");
    }

    @RequestMapping(value = "/exist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "查询客户是否存在")
    public Responsed<Boolean> existByAccountIdAndWxno(HttpServletRequest request, String wxno){
        String  accountId = (String) request.getSession().getAttribute(CacheName.ACCOUNT_COOKIE);
        boolean exist     = clientService.existByAccountIdAndWxno(accountId, wxno);

        return new Responsed<Boolean>("查询成功", exist);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "查询客户信息列表")
    public Responsed<List<Client>> find(HttpServletRequest request, String name, String wxno, String remark){
        String       accountId = (String) request.getSession().getAttribute(CacheName.ACCOUNT_COOKIE);
        List<Client> list      = clientService.find(accountId, name, wxno, remark);

        return new Responsed<List<Client>>("查询成功", list);
    }
}
