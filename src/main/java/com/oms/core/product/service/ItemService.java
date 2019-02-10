package com.oms.core.product.service;

import com.oms.commons.consts.ProductType;
import com.oms.core.product.entity.Item;
import com.wah.doraemon.utils.Page;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService{

    void upload(MultipartFile file) throws Exception;

    void updateIsCheck(List<String> ids, Boolean isCheck);

    void updateStock(String id, Integer stock);

    SXSSFWorkbook exportTemplate();

    Page<Item> page(Long pageNum, Long pageSize, String code, String name, String productName, ProductType type);
}
