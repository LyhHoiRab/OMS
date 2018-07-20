package com.oms.core.product.service;

import com.oms.core.product.dao.ItemDao;
import com.oms.core.product.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

@Service
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemDao itemDao;

    @Override
    public Page<Item> page(PageRequest pageRequest, String itemCode, String itemName, Integer minNum, Integer maxNum,
                           Integer minSellNum, Integer maxSellNum){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return itemDao.page(pageRequest, itemCode, itemName, minNum, maxNum, minSellNum, maxSellNum);
    }
}
