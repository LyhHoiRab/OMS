package com.oms.core.permission.service;

import com.oms.core.permission.dao.MenuDao;
import com.oms.core.permission.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;

    @Transactional
    @Override
    public void save(Menu menu){
        Assert.notNull(menu, "菜单信息不能为空");
        Assert.hasText(menu.getRouterPath(), "路由路径不能为空");
        Assert.notNull(menu.getAllocatable(), "分配状态不能为空");

        menuDao.saveOrUpdate(menu);
    }

    @Transactional
    @Override
    public void update(Menu menu){
        Assert.notNull(menu, "菜单信息不能为空");
        Assert.hasText(menu.getId(), "ID不能为空");

        menuDao.saveOrUpdate(menu);
    }
}
