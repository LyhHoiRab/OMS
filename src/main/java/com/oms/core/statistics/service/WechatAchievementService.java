package com.oms.core.statistics.service;

import com.oms.core.statistics.entity.WechatAchievement;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Date;
import java.util.List;

public interface WechatAchievementService{

    List<WechatAchievement> find(String sellerName, String wxNo, Date minDateCreated, Date maxDateCreated);

    XSSFWorkbook export(String sellerName, String wxNo, Date minDateCreated, Date maxDateCreated);
}
