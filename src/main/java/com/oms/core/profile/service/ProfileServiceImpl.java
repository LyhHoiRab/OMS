package com.oms.core.profile.service;

import com.oms.core.profile.dao.ProfileDao;
import com.oms.core.profile.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private ProfileDao profileDao;

    @Override
    public List<Profile> findCsad(){
        return profileDao.findCsad(null);
    }

    @Override
    public Page<Profile> pageBySellers(PageRequest pageRequest, String realName){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return profileDao.pageBySellers(pageRequest, realName);
    }
}
