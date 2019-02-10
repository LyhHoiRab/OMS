package com.oms.core.account.service;

import com.oms.commons.consts.AccountStatus;
import com.oms.commons.consts.PermissionConfig;
import com.oms.commons.consts.Sex;
import com.oms.core.account.dao.AccountDao;
import com.oms.core.account.dao.SalesDao;
import com.oms.core.account.dao.UserDao;
import com.oms.core.account.entity.Account;
import com.oms.core.account.entity.User;
import com.wah.doraemon.security.exception.DuplicateException;
import com.wah.doraemon.utils.IDUtils;
import com.wah.doraemon.utils.Page;
import com.wah.doraemon.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SalesServiceImpl implements SalesService{

    @Autowired
    private SalesDao salesDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void save(User sales){
        Account account = sales.getAccount();

        Assert.notNull(sales, "销售信息不能为空");
        Assert.notNull(account, "账户信息不能为空");
        Assert.hasText(account.getUsername(), "账户名不能为空");
        Assert.hasText(account.getPassword(), "账户密码不能为空");

        if(accountDao.existByUsername(account.getUsername())){
            throw new DuplicateException("该账户[{0}]已注册", account.getUsername());
        }

        //保存账户信息
        accountDao.saveOrUpdate(account);

        //保存用户信息
        sales.setAccountId(account.getId());
        userDao.saveOrUpdate(sales);

        //添加销售角色
        accountDao.grant(account.getId(), Arrays.asList(PermissionConfig.SALES_ID));
    }

    @Transactional
    @Override
    public void update(User sales){
        Account account = sales.getAccount();

        Assert.notNull(sales, "销售信息不能为空");
        Assert.notNull(account, "账户信息不能为空");
        Assert.hasText(sales.getId(), "销售ID不能为空");
        Assert.hasText(account.getId(), "账户ID不能为空");

        //修改账户信息
        accountDao.saveOrUpdate(account);

        //修改用户信息
        userDao.saveOrUpdate(sales);
    }

    @Transactional
    @Override
    public void updateStatus(List<String> accountIds, AccountStatus status){
        Assert.notEmpty(accountIds, "账户ID列表不能为空");
        Assert.notNull(status, "账户状态不能为空");

        salesDao.updateStatus(accountIds, status);
    }

    @Override
    public User getByAccountId(String accountId){
        Assert.hasText(accountId, "账户ID不能为空");

        return salesDao.getByAccountId(accountId);
    }

    @Override
    public Page<User> page(Long pageNum, Long pageSize, String username, String nickname, AccountStatus status){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);

        return salesDao.page(pageRequest, username, nickname, status);
    }

    @Override
    public List<User> find(String username, String nickname, AccountStatus status){
        return salesDao.find(username, nickname, status);
    }
}
