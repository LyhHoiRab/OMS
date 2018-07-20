package com.oms.core.statistics.webservice;

import com.oms.core.statistics.entity.Achievement;
import com.oms.core.statistics.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/achievement")
public class AchievementRestController{

    @Autowired
    private AchievementService achievementService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<Achievement>> achievement(){
        List<Achievement> list = achievementService.achievement();

        return new Responsed<List<Achievement>>("查询成功", list);
    }
}
