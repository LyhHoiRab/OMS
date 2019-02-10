package com.oms.core.product.service;

import com.google.common.collect.Lists;
import com.oms.commons.consts.Customized;
import com.oms.commons.consts.ProductType;
import com.oms.commons.utils.ItemExcelUtils;
import com.oms.core.product.dao.ItemDao;
import com.oms.core.product.dao.ProductDao;
import com.oms.core.product.entity.Item;
import com.oms.core.product.entity.Product;
import com.wah.doraemon.security.exception.DataNotFoundException;
import com.wah.doraemon.utils.Page;
import com.wah.doraemon.utils.PageRequest;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public void upload(MultipartFile file) throws Exception{
        Assert.notNull(file, "导入的商品Excel文件不能为空");

        List<Item>    list       = ItemExcelUtils.upload(file);
        List<Item>    original   = itemDao.find();
        List<Item>    saveList   = Lists.newArrayList();
        List<Item>    updateList = Lists.newArrayList();
        List<Product> products   = Lists.newArrayList();

        if(!list.isEmpty()){
            for(Item item : list){
                if(original.contains(item)){
                    updateList.add(item);
                }else{
                    saveList.add(item);

                    //创建产品
                    Product product = new Product();
                    product.setCode(item.getCode());
                    product.setCustomized(Customized.SKIN_CARE);
                    product.setType(ProductType.OTHER);
                    product.setName(item.getName());
                    product.setPrice(0d);
                    products.add(product);
                }
            }
        }

        if(!saveList.isEmpty()){
            itemDao.saveList(saveList);
        }
        if(!updateList.isEmpty()){
            itemDao.updateList(updateList);
        }
        if(!products.isEmpty()){
            productDao.saveList(products);
        }
    }

    @Transactional
    @Override
    public void updateIsCheck(List<String> ids, Boolean isCheck){
        Assert.notEmpty(ids, "商品ID列表不能为空");
        Assert.notNull(isCheck, "商品上架状态不能为空");

        itemDao.updateIsCheck(ids, isCheck);
    }

    @Transactional
    @Override
    public void updateStock(String id, Integer stock){
        Assert.hasText(id, "商品ID不能为空");
        Assert.notNull(stock, "商品库存不能为空");

        Item item = itemDao.getById(id);
        if(item == null){
            throw new DataNotFoundException("没有该商品信息");
        }

        item.setStock(stock);
        itemDao.update(item);
    }

    @Override
    public SXSSFWorkbook exportTemplate(){
        return ItemExcelUtils.exportTemplate();
    }

    @Override
    public Page<Item> page(Long pageNum, Long pageSize, String code, String name, String productName, ProductType type){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);

        return itemDao.page(pageRequest, code, name, productName, type);
    }
}
