package com.oms.core.product.service;

import com.oms.core.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService{

    String uploadCertificate(MultipartFile file) throws Exception;

    String uploadThumbnail(MultipartFile file) throws Exception;

    Product getById(String id);

    void update(Product product);
}
