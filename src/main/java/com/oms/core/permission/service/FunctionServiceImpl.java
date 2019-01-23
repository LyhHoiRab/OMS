package com.oms.core.permission.service;

import com.google.common.collect.Lists;
import com.oms.core.permission.dao.FunctionDao;
import com.oms.core.permission.dao.RoleDao;
import com.oms.core.permission.entity.Function;
import com.oms.core.permission.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FunctionServiceImpl implements FunctionService{

    @Autowired
    private FunctionDao functionDao;

    @Autowired
    private RoleDao roleDao;

//    @PostConstruct
    @Transactional
    @Override
    public void sync(){
        List<Function> original   = functionDao.find();
        List<Function> current    = functionDao.current();
        List<Function> saveList   = Lists.newArrayList();
        List<Function> updateList = Lists.newArrayList();
        List<Function> deleteList = Lists.newArrayList();

        if(!current.isEmpty()){
            for(Function function : current){
                if(original.contains(function)){
                    updateList.add(function);
                }else{
                    //设置默认权限
                    function.setCookie(true);
                    function.setGranted(true);
                    function.setAllocatable(true);

                    saveList.add(function);
                }
            }

            for(Function function : original){
                if(!current.contains(function)){
                    deleteList.add(function);
                }
            }

        }else{
            //当前没有任何功能API
            deleteList = original;
        }

        if(!saveList.isEmpty()){
            functionDao.saveList(saveList);
        }
        if(!updateList.isEmpty()){
            functionDao.updateList(updateList);
        }
        if(!deleteList.isEmpty()){
            functionDao.deleteList(deleteList);
        }

        //添加到缓存
        functionDao.cacheNeedNotCookie();
        functionDao.cacheNeedNotGrant();

        //缓存角色权限
        List<Role> roles = roleDao.findWithFunctions();
        for(Role role : roles){
            functionDao.cacheByRoleId(role.getId(), role.getFunctions());
        }
    }
}
