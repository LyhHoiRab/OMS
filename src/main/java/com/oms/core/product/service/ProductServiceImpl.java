package com.oms.core.product.service;

import com.oms.commons.consts.UpyunConfig;
import com.oms.commons.security.exception.UpyunException;
import com.oms.commons.utils.UpyunUtils;
import com.oms.core.product.dao.ProductDao;
import com.oms.core.product.entity.Product;
import com.wah.doraemon.utils.FileUtils;
import main.java.com.UpYun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    public String uploadCertificate(MultipartFile file) throws Exception{
        Assert.notNull(file, "产品备案证书图片不能为空");

        //二进制
        byte[] bytes  = file.getBytes();
        //后缀
        String suffix = FileUtils.getSuffix(file.getOriginalFilename());
        //MD5
        String md5    = FileUtils.getMD5(bytes);
        //上传目录
        String path   = UpyunConfig.getCertificateDir() + md5 + "." + suffix;

        UpYun client = UpyunUtils.create(UpyunConfig.OPERATOR, UpyunConfig.BUCKET, UpyunConfig.PASSWORD);

        if(UpyunUtils.upload(client, path, bytes)){
            return UpyunConfig.URL + path;
        }

        throw new UpyunException("备案证书上传失败");
    }

    @Override
    public String uploadThumbnail(MultipartFile file) throws Exception{
        Assert.notNull(file, "产品图片不能为空");

        //二进制
        byte[] bytes  = file.getBytes();
        //后缀
        String suffix = FileUtils.getSuffix(file.getOriginalFilename());
        //MD5
        String md5    = FileUtils.getMD5(bytes);
        //上传目录
        String path   = UpyunConfig.getThumbnailDir() + md5 + "." + suffix;

        UpYun client = UpyunUtils.create(UpyunConfig.OPERATOR, UpyunConfig.BUCKET, UpyunConfig.PASSWORD);

        if(UpyunUtils.upload(client, path, bytes)){
            return UpyunConfig.URL + path;
        }

        throw new UpyunException("产品图片上传失败");
    }

    @Override
    public Product getById(String id){
        Assert.hasText(id, "产品ID不能为空");

        return productDao.getById(id);
    }

    @Transactional
    @Override
    public void update(Product product){
        Assert.notNull(product, "产品信息不能为空");
        Assert.hasText(product.getId(), "产品ID不能为空");

        productDao.saveOrUpdate(product);
    }
}
