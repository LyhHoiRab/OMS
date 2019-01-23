package com.oms.core.permission.dao;

import com.wah.doraemon.security.exception.DataAccessException;
import com.wah.doraemon.utils.IDUtils;
import com.oms.core.permission.dao.mapper.MenuMapper;
import com.oms.core.permission.entity.Menu;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;

@Repository
public class MenuDao{

    private Logger logger = LoggerFactory.getLogger(MenuDao.class);

    @Autowired
    private MenuMapper mapper;

    public void saveOrUpdate(Menu menu){
        try{
            Assert.notNull(menu, "菜单信息不能为空");

            if(StringUtils.isBlank(menu.getId())){
                Assert.hasText(menu.getRouterPath(), "路由路径不能为空");
                Assert.notNull(menu.getAllocatable(), "分配状态不能为空");

                menu.setId(IDUtils.uuid32());
                menu.setCreateTime(new Date());
                mapper.save(menu);
            }else{
                menu.setUpdateTime(new Date());
                mapper.update(menu);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
