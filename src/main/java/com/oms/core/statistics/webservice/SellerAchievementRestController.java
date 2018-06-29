package com.oms.core.statistics.webservice;

import com.oms.core.statistics.entity.SellerAchievement;
import com.oms.core.statistics.service.SellerAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/achievement/seller")
public class SellerAchievementRestController{

    @Autowired
    private SellerAchievementService sellerAchievementService;

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<SellerAchievement>> find(String sellerName, Date minDateCreated, Date maxDateCreated){
        List<SellerAchievement> list = sellerAchievementService.find(sellerName, minDateCreated, maxDateCreated);

        return new Responsed<List<SellerAchievement>>("查询成功", list);
    }
}
