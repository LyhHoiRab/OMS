package com.oms.core.account.webservice;

import com.wah.doraemon.security.response.Responsed;
import com.oms.commons.consts.CacheName;
import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.account.entity.Account;
import com.oms.core.account.entity.User;
import com.oms.core.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/account")
public class AccountRestController{

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "账户注册")
    public Responsed register(@RequestBody Account account){
        accountService.register(account);

        return new Responsed("注册成功");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "账户登录")
    public Responsed<User> login(HttpServletRequest request, String username, String password){
        User user = accountService.login(username, password);
        //设置session
        request.getSession().setAttribute(CacheName.ACCOUNT_COOKIE, user.getAccountId());

        return new Responsed<User>("登录成功", user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "账户登出")
    public Responsed logout(HttpServletRequest request){
        HttpSession session   = request.getSession();
        String      accountId = (String) session.getAttribute(CacheName.ACCOUNT_COOKIE);
        //清除缓存
        accountService.logout(accountId, session.getId());

        return new Responsed("登出成功");
    }

    @RequestMapping(value = "/grant/{accountId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "账户授权")
    public Responsed grant(@PathVariable("accountId") String accountId, @RequestBody List<String> roleIds){
        accountService.grant(accountId, roleIds);

        return new Responsed("授权成功");
    }
}
