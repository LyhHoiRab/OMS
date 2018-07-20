package com.oms.core.product.webservice;

import com.oms.core.product.entity.Item;
import com.oms.core.product.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/item")
public class ItemRestController{

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Item>> page(Long pageNum, Long pageSize, String itemCode, String itemName, Integer minNum,
                                      Integer maxNum, Integer minSellNum, Integer maxSellNum){

        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Item> page = itemService.page(pageRequest, itemCode, itemName, minNum, maxNum, minSellNum, maxSellNum);

        return new Responsed<Page<Item>>("查询成功", page);
    }
}
