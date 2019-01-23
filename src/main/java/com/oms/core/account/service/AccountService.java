package com.oms.core.account.service;

import com.oms.core.account.entity.Account;
import com.oms.core.account.entity.User;

import java.util.List;

public interface AccountService{

    void register(Account account);

    User login(String username, String password);

    void logout(String accountId, String sessionId);

    void grant(String accountId, List<String> roleIds);
}
