package com.oms.core.statistics.service;

import com.oms.core.statistics.entity.SellerAchievement;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Date;
import java.util.List;

public interface SellerAchievementService{

    List<SellerAchievement> find(String sellerName, Date minDateCreated, Date maxDateCreated);

    XSSFWorkbook export(String sellerName, Date minDateCreated, Date maxDateCreated);
}
