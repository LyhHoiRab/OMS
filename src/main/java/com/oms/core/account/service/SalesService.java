package com.oms.core.account.service;

import com.oms.commons.consts.AccountStatus;
import com.oms.core.account.entity.User;
import com.wah.doraemon.utils.Page;

import java.util.List;

public interface SalesService{

    void save(User sales);

    void update(User sales);

    void updateStatus(List<String> accountIds, AccountStatus status);

    User getByAccountId(String accountId);

    Page<User> page(Long pageNum, Long pageSize, String username, String nickname, AccountStatus status);

    List<User> find(String username, String nickname, AccountStatus status);
}
