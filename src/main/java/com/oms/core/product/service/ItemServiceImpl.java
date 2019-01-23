package com.oms.core.product.service;

import com.google.common.collect.Lists;
import com.oms.commons.consts.Customized;
import com.oms.commons.consts.ProductType;
import com.oms.commons.utils.ItemExcelUtils;
import com.oms.core.product.dao.ItemDao;
import com.oms.core.product.dao.ProductDao;
import com.oms.core.product.entity.Item;
import com.oms.core.product.entity.Product;
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

        List<Item> list        = ItemExcelUtils.upload(file);
        List<Item> original    = itemDao.find();
        List<Item> saveList    = Lists.newArrayList();
        List<Item> updateList  = Lists.newArrayList();
        List<Product> products = Lists.newArrayList();

        if(!list.isEmpty()){
            for(Item item : list){
                if(original.contains(item)){
                    updateList.add(item);
                }else{
                    saveList.add(item);

                    //创建产品
                    Product product = new Product();
                    product.setCode(item.getCode());
                    product.setCustomized(Customized.PRIVACY_CARE);
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
