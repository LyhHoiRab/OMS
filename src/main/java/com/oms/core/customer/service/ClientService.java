package com.oms.core.customer.service;

import com.oms.core.customer.entity.Client;

import java.util.List;

public interface ClientService{

    void save(Client client);

    void update(Client client);

    boolean existByAccountIdAndWxno(String accountId, String wxno);

    List<Client> find(String accountId, String name, String wxno, String remark);

    Client getById(String id);
}
