package com.oms.core.popularize.service;

import com.oms.commons.consts.UpyunConfig;
import com.oms.commons.security.exception.UpyunException;
import com.oms.commons.utils.UpyunUtils;
import com.oms.core.popularize.dao.WxInfoDao;
import com.oms.core.popularize.entity.WxInfo;
import com.wah.doraemon.utils.FileUtils;
import com.wah.doraemon.utils.Page;
import com.wah.doraemon.utils.PageRequest;
import main.java.com.UpYun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public class WxInfoServiceImpl implements WxInfoService{

    @Autowired
    private WxInfoDao wxInfoDao;

    @Override
    public String uploadImage(MultipartFile file) throws Exception{
        Assert.notNull(file, "上传的微信图片不能为空");

        //二进制
        byte[] bytes  = file.getBytes();
        //后缀
        String suffix = FileUtils.getSuffix(file.getOriginalFilename());
        //MD5
        String md5    = FileUtils.getMD5(bytes);
        //上传目录
        String path   = UpyunConfig.getWxImageDir() + md5 + "." + suffix;

        UpYun client = UpyunUtils.create(UpyunConfig.OPERATOR, UpyunConfig.BUCKET, UpyunConfig.PASSWORD);

        if(UpyunUtils.upload(client, path, bytes)){
            return UpyunConfig.URL + path;
        }

        throw new UpyunException("微信图片上传失败");
    }

    @Override
    public String uploadQr(MultipartFile file) throws Exception{
        Assert.notNull(file, "上传的微信二维码不能为空");

        //二进制
        byte[] bytes  = file.getBytes();
        //后缀
        String suffix = FileUtils.getSuffix(file.getOriginalFilename());
        //MD5
        String md5    = FileUtils.getMD5(bytes);
        //上传目录
        String path   = UpyunConfig.getWxQrDir() + md5 + "." + suffix;

        UpYun client = UpyunUtils.create(UpyunConfig.OPERATOR, UpyunConfig.BUCKET, UpyunConfig.PASSWORD);

        if(UpyunUtils.upload(client, path, bytes)){
            return UpyunConfig.URL + path;
        }

        throw new UpyunException("微信二维码上传失败");
    }

    @Transactional
    @Override
    public void save(WxInfo wxInfo){
        Assert.notNull(wxInfo, "微信信息不能为空");
        Assert.hasText(wxInfo.getWxno(), "微信号不能为空");

        wxInfoDao.saveOrUpdate(wxInfo);
    }

    @Transactional
    @Override
    public void update(WxInfo wxInfo){
        Assert.notNull(wxInfo, "微信信息不能为空");
        Assert.hasText(wxInfo.getId(), "微信ID不能为空");

        wxInfoDao.saveOrUpdate(wxInfo);
    }

    @Override
    public WxInfo getById(String id){
        Assert.hasText(id, "微信ID不能为空");

        return wxInfoDao.getById(id);
    }

    @Override
    public Page<WxInfo> page(Long pageNum, Long pageSize, String wxno, String nickname, String phone, String remark, String sales){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);

        return wxInfoDao.page(pageRequest, wxno, nickname, phone, remark, sales);
    }
}
