package com.oms.core.popularize.service;

import com.oms.core.popularize.dao.WxInfoDao;
import com.oms.core.popularize.entity.WxInfo;
import com.wah.doraemon.utils.Page;
import com.wah.doraemon.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
public class WxInfoServiceImpl implements WxInfoService{

    @Autowired
    private WxInfoDao wxInfoDao;

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
    public Page<WxInfo> page(Long pageNum, Long pageSize, String wxno, String nickname, String phone, String remark){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);

        return wxInfoDao.page(pageRequest, wxno, nickname, phone, remark);
    }
}
