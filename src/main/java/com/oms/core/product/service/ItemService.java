package com.oms.core.product.service;

import com.oms.core.product.entity.Item;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface ItemService{

    Page<Item> page(PageRequest pageRequest, String itemCode, String itemName, Integer minNum, Integer maxNum,
                    Integer minSellNum, Integer maxSellNum);
}
