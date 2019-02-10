package com.oms.core.permission.service;

import com.oms.core.permission.entity.Role;
import com.wah.doraemon.domain.consts.UsingStatus;
import com.wah.doraemon.utils.Page;

import java.util.List;

public interface RoleService{

    void save(Role role);

    void update(Role role);

    void grant(String roleId, List<String> functionIds);

    Page<Role> page(Long pageNum, Long pageSize, String name, Boolean isSystem, UsingStatus status);
}
