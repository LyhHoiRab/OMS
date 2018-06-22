package com.oms.core.profile.service;

import com.oms.core.profile.dao.ProfileDao;
import com.oms.core.profile.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private ProfileDao profileDao;

    public List<Profile> findCsad(){
        return profileDao.findCsad(null);
    }
}
