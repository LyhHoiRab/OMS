package com.oms.core.statistics.service;

import com.oms.core.statistics.entity.WechatAchievement;

import java.util.Date;
import java.util.List;

public interface WechatAchievementService{

    List<WechatAchievement> find(String sellerName, String wxNo, Date minDateCreated, Date maxDateCreated);
}
