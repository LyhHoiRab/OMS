package com.oms.core.product.webservice;

import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.product.entity.Product;
import com.oms.core.product.service.ProductService;
import com.wah.doraemon.security.response.Responsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/1.0/product")
public class ProductRestController{

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/upload/certificate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "产品备案证书上传")
    public Responsed<String> uploadCertificate(MultipartFile file) throws Exception{
        String path = productService.uploadCertificate(file);

        return new Responsed<String>("上传成功", path);
    }

    @RequestMapping(value = "/upload/thumbnail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "产品图片上传")
    public Responsed<String> uploadThumbnail(MultipartFile file) throws Exception{
        String path = productService.uploadThumbnail(file);

        return new Responsed<String>("上传成功", path);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "根据ID查询产品信息")
    public Responsed<Product> getById(@PathVariable("id") String id){
        Product product = productService.getById(id);

        return new Responsed<Product>("查询成功", product);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "修改产品")
    public Responsed update(@RequestBody Product product){
        productService.update(product);

        return new Responsed("修改成功");
    }
}
