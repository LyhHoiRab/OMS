package com.oms.core.profile.service;

import com.oms.core.profile.entity.Profile;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

public interface ProfileService{

    List<Profile> findCsad();

    Page<Profile> pageBySellers(PageRequest pageRequest, String realName);
}
