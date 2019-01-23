package com.oms.core.permission.service;

import com.oms.core.permission.entity.Role;

import java.util.List;

public interface RoleService{

    void save(Role role);

    void update(Role role);

    void grant(String roleId, List<String> functionIds);
}
