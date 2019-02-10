package com.oms.core.permission.service;

import com.google.common.collect.Lists;
import com.oms.core.permission.dao.FunctionDao;
import com.oms.core.permission.dao.RoleDao;
import com.oms.core.permission.entity.Role;
import com.wah.doraemon.domain.consts.UsingStatus;
import com.wah.doraemon.utils.Page;
import com.wah.doraemon.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private FunctionDao functionDao;

    @Transactional
    @Override
    public void save(Role role){
        Assert.notNull(role, "角色信息不能为空");

        roleDao.saveOrUpdate(role);
    }

    @Transactional
    @Override
    public void update(Role role){
        Assert.notNull(role, "角色信息不能为空");
        Assert.hasText(role.getId(), "ID不能为空");

        roleDao.saveOrUpdate(role);
    }

    @Transactional
    @Override
    public void grant(String roleId, List<String> functionIds){
        Assert.hasText(roleId, "角色ID不能为空");

        //更新权限
        roleDao.grant(roleId, functionIds);

        //更新缓存
        if(functionIds.isEmpty()){
            functionDao.cacheByRoleId(roleId, Lists.newArrayList());
        }else{
            functionDao.cacheByRoleId(roleId, functionDao.findByIds(functionIds));
        }
    }

    @Override
    public Page<Role> page(Long pageNum, Long pageSize, String name, Boolean isSystem, UsingStatus status){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);

        return roleDao.page(pageRequest, name, isSystem, status);
    }
}
