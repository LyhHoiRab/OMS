package com.oms.core.statistics.webservice;

import com.oms.core.statistics.entity.WechatAchievement;
import com.oms.core.statistics.service.WechatAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/achievement/wechat")
public class WechatAchievementRestController{

    @Autowired
    private WechatAchievementService wechatAchievementService;

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<WechatAchievement>> find(String sellerName, String wxNo, Date minDateCreated, Date maxDateCreated){
        List<WechatAchievement> list = wechatAchievementService.find(sellerName, wxNo, minDateCreated, maxDateCreated);

        return new Responsed<List<WechatAchievement>>("查询成功", list);
    }
}
