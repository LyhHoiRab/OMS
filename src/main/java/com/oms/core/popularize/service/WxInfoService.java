package com.oms.core.popularize.service;

import com.oms.core.popularize.entity.WxInfo;
import com.wah.doraemon.utils.Page;
import org.springframework.web.multipart.MultipartFile;

public interface WxInfoService{

    String uploadImage(MultipartFile file) throws Exception;

    String uploadQr(MultipartFile file) throws Exception;

    void save(WxInfo wxInfo);

    void update(WxInfo wxInfo);

    WxInfo getById(String id);

    Page<WxInfo> page(Long pageNum, Long pageSize, String wxno, String nickname, String phone, String remark, String sales);
}
