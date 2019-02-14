package com.oms.core.customer.service;

import com.oms.core.customer.dao.ClientDao;
import com.oms.core.customer.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientDao clientDao;

    @Transactional
    @Override
    public void save(Client client){
        Assert.notNull(client, "客户信息不能我为空");
        Assert.hasText(client.getAccountId(), "关联账户ID不能为空");
        Assert.hasText(client.getName(), "客户姓名不能为空");
        Assert.hasText(client.getWxno(), "客户微信不能为空");

        clientDao.saveOrUpdate(client);
    }

    @Transactional
    @Override
    public void update(Client client){
        Assert.notNull(client, "客户信息不能我为空");
        Assert.hasText(client.getId(), "客户ID不能为空");

        clientDao.saveOrUpdate(client);
    }

    @Override
    public boolean existByAccountIdAndWxno(String accountId, String wxno){
        Assert.hasText(accountId, "关联的账户ID不能为空");
        Assert.hasText(wxno, "客户微信号不能为空");

        return clientDao.existByAccountIdAndWxno(accountId, wxno);
    }

    @Override
    public List<Client> find(String accountId, String name, String wxno, String remark){
        return clientDao.find(accountId, name, wxno, remark);
    }

    @Override
    public Client getById(String id){
        Assert.hasText(id, "客户ID不能为空");

        return clientDao.getById(id);
    }
}
