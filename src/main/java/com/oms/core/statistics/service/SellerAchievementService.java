package com.oms.core.statistics.service;

import com.oms.core.statistics.entity.SellerAchievement;

import java.util.Date;
import java.util.List;

public interface SellerAchievementService{

    List<SellerAchievement> find(String sellerName, Date minDateCreated, Date maxDateCreated);
}
