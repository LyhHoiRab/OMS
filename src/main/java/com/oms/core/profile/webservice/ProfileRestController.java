package com.oms.core.profile.webservice;

import com.oms.core.profile.entity.Profile;
import com.oms.core.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/profile")
public class ProfileRestController{

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/csad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<Profile>> findCsad(){
        List<Profile> list = profileService.findCsad();

        return new Responsed<List<Profile>>("查询成功", list);
    }
}
