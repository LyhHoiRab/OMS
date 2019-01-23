package com.oms.core.product.service;

import com.oms.commons.consts.ProductType;
import com.oms.core.product.entity.Item;
import com.wah.doraemon.utils.Page;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService{

    void upload(MultipartFile file) throws Exception;

    SXSSFWorkbook exportTemplate();

    Page<Item> page(Long pageNum, Long pageSize, String code, String name, String productName, ProductType type);
}
