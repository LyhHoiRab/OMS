package com.oms.core.account.service;

import com.google.common.collect.Lists;
import com.wah.doraemon.security.exception.DuplicateException;
import com.wah.doraemon.utils.IDUtils;
import com.wah.doraemon.utils.RSAUtils;
import com.oms.commons.consts.Sex;
import com.oms.commons.security.exception.AccountNotFoundException;
import com.oms.commons.security.exception.AccountStatusException;
import com.oms.core.account.dao.AccountDao;
import com.oms.core.account.dao.SessionDao;
import com.oms.core.account.dao.UserDao;
import com.oms.core.account.entity.Account;
import com.oms.core.account.entity.User;
import com.oms.core.key.dao.KeyDao;
import com.oms.core.permission.dao.RoleDao;
import com.oms.core.permission.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private KeyDao keyDao;

    @Autowired
    private SessionDao sessionDao;

    @Override
    @Transactional
    public void register(Account account){
        Assert.notNull(account, "账户信息不能为空");
        Assert.hasText(account.getUsername(), "账户名不能为空");
        Assert.hasText(account.getPassword(), "账户密码不能为空");

        if(accountDao.existByUsername(account.getUsername())){
            throw new DuplicateException("该账户[{0}]已注册", account.getUsername());
        }

        //保存账户信息
        accountDao.saveOrUpdate(account);

        //保存用户信息
        User user = new User();
        user.setAccountId(account.getId());
        user.setNickname(IDUtils.random());
        user.setSex(Sex.UNKNOWN);
        userDao.saveOrUpdate(user);
    }

    @Override
    public User login(String username, String password){
        Assert.hasText(username, "账户名不能为空");
        Assert.hasText(password, "账户密码不能为空");

        Account account = accountDao.getByUsername(username);

        //验证用户名
        if(account == null){
            throw new AccountNotFoundException("账户名或密码不正确");
        }

        //验证密码
        if(!RSAUtils.equalsByPrivateKey(account.getPassword(), password, keyDao.getRSAPrivateKey())){
            throw new AccountNotFoundException("账户名或密码不正确");
        }

        switch(account.getStatus()){
            case NORMAL:
                break;
            case FROZEN:
                throw new AccountStatusException("账户已被冻结");
            case LOCKED:
                throw new AccountStatusException("账户已被锁定");
            default:
                throw new AccountStatusException("账户状态异常");
        }

        //查找用户信息
        User user = userDao.getByAccountId(account.getId());
        //缓存
        userDao.cache(user);

        //账户角色
        List<Role> roles = roleDao.findByAccountId(account.getId());
        //缓存
        roleDao.cacheByAccountId(account.getId(), roles);

        return user;
    }

    @Transactional
    @Override
    public void logout(String accountId, String sessionId){
        Assert.hasText(accountId, "账户ID不能为空");
        Assert.hasText(sessionId, "会话ID不能为空");

        //清除账户信息缓存
        accountDao.clearCacheById(accountId);
        //清除账户角色缓存
        roleDao.clearCacheByAccountId(accountId);
        //清除session缓存
        sessionDao.clearCacheByById(sessionId);
    }

    @Transactional
    @Override
    public void grant(String accountId, List<String> roleIds){
        Assert.hasText(accountId, "账户ID不能为空");

        //更新权限
        accountDao.grant(accountId, roleIds);

        //更新缓存
        if(accountDao.existCacheById(accountId)){
            if(roleIds.isEmpty()){
                roleDao.cacheByAccountId(accountId, Lists.newArrayList());
            }else{
                roleDao.cacheByAccountId(accountId, roleDao.findByIds(roleIds));
            }
        }
    }
}
