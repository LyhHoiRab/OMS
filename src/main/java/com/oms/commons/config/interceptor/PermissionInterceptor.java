package com.oms.commons.config.interceptor;

import com.wah.doraemon.security.response.Responsed;
import com.wah.doraemon.security.response.consts.ResponseCode;
import com.wah.doraemon.utils.GsonUtils;
import com.oms.commons.consts.CacheName;
import com.oms.commons.consts.PermissionConfig;
import com.oms.core.account.dao.AccountDao;
import com.oms.core.permission.dao.FunctionDao;
import com.oms.core.permission.dao.RoleDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private FunctionDao functionDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //获取请求路径
        String url    = request.getRequestURI().substring(request.getContextPath().length());
        String method = request.getMethod();

        //不需要登录的功能
        if(functionDao.needNotCookieByCache(url, method)){
            return true;
        }

        //检查登录状态
        String accountId = (String) request.getSession().getAttribute(CacheName.ACCOUNT_COOKIE);
        if(StringUtils.isBlank(accountId) || !accountDao.existCacheById(accountId)){
            //没有登录
            Responsed responsed = new Responsed("未登录状态", ResponseCode.NOT_LOGIN_STATUS, false);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(GsonUtils.serialize(responsed));
            return false;
        }

        //不需要授权的功能
        if(functionDao.needNotGrantedByCache(url, method)){
            return true;
        }

        //查询用户角色
        Set<String> roles = roleDao.findCacheByAccountId(accountId);

        //超级管理员
        if(roles.contains(PermissionConfig.ADMINISTRATOR_ID)){
            return true;
        }

        //检查用户权限
        for(String role : roles){
            if(roleDao.granted(role, url, method)){
                return true;
            }
        }

        //没有权限
        Responsed responsed = new Responsed("无访问权限", ResponseCode.FORBIDDEN_VISIT, false);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(GsonUtils.serialize(responsed));
        return false;
    }
}
