package com.oms.core.statistics.service;

import com.oms.core.statistics.dao.AchievementDao;
import com.oms.core.statistics.dao.PayTypeStatisticsDao;
import com.oms.core.statistics.entity.Achievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wah.doraemon.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AchievementServiceImpl implements AchievementService{

    @Autowired
    private AchievementDao achievementDao;

    @Autowired
    private PayTypeStatisticsDao payTypeStatisticsDao;

    @Override
    public List<Achievement> achievement(){
        Date now = new Date();

        //前天
        Date beforeYesterdayMinDateCreated = DateUtils.addDays(DateUtils.firstTimeOfDay(now), -2);
        Date beforeYesterdayMaxDateCreated = DateUtils.addDays(DateUtils.lastTimeOfDay(now), -2);
        //统计
        Achievement beforeYesterday = achievementDao.achievement(beforeYesterdayMinDateCreated, beforeYesterdayMaxDateCreated);
        beforeYesterday.setTitle("前天");
        payTypeStatisticsDao.fill(beforeYesterday, beforeYesterdayMinDateCreated, beforeYesterdayMaxDateCreated);

        //昨天
        Date yesterdayMinDateCreated = DateUtils.addDays(DateUtils.firstTimeOfDay(now), -1);
        Date yesterdayMaxDateCreated = DateUtils.addDays(DateUtils.lastTimeOfDay(now), -1);
        //统计
        Achievement yesterday = achievementDao.achievement(yesterdayMinDateCreated, yesterdayMaxDateCreated);
        yesterday.setTitle("昨天");
        payTypeStatisticsDao.fill(yesterday, yesterdayMinDateCreated, yesterdayMaxDateCreated);

        //今天
        Date todayMinDateCreated = DateUtils.firstTimeOfDay(now);
        Date todayMaxDateCreated = DateUtils.lastTimeOfDay(now);
        //统计
        Achievement today = achievementDao.achievement(todayMinDateCreated, todayMaxDateCreated);
        today.setTitle("今天");
        payTypeStatisticsDao.fill(today, todayMinDateCreated, todayMaxDateCreated);

        //上月
        Date lastMonthMinDateCreated = DateUtils.firstTimeOfMonth(DateUtils.addMonths(now, -1));
        Date lastMonthMaxDateCreated = DateUtils.lastTimeOfMonth(DateUtils.addMonths(now, -1));
        //统计
        Achievement lastMonth = achievementDao.achievement(lastMonthMinDateCreated, lastMonthMaxDateCreated);
        lastMonth.setTitle("上月");
        payTypeStatisticsDao.fill(lastMonth, lastMonthMinDateCreated, lastMonthMaxDateCreated);

        //当月
        Date currentMonthMinDateCreated = DateUtils.firstTimeOfMonth(now);
        Date currentMonthMaxDateCreated = DateUtils.lastTimeOfMonth(now);
        //统计
        Achievement currentMonth = achievementDao.achievement(currentMonthMinDateCreated, currentMonthMaxDateCreated);
        currentMonth.setTitle("当月");
        payTypeStatisticsDao.fill(currentMonth, currentMonthMinDateCreated, currentMonthMaxDateCreated);

        //上年
        Date lastYearMinDateCreated = DateUtils.firstTimeOfYear(DateUtils.addYears(now,-1));
        Date lastYearMaxDateCreated = DateUtils.lastTimeOfYear(DateUtils.addYears(now, -1));
        //统计
        Achievement lastYear = achievementDao.achievement(lastYearMinDateCreated, lastYearMaxDateCreated);
        lastYear.setTitle("上年");
        payTypeStatisticsDao.fill(lastYear, lastYearMinDateCreated, lastYearMaxDateCreated);

        //当年
        Date currentYearMinDateCreated = DateUtils.firstTimeOfYear(now);
        Date currentYearMaxDateCreated = DateUtils.lastTimeOfYear(now);
        //统计
        Achievement currentYear = achievementDao.achievement(currentYearMinDateCreated, currentYearMaxDateCreated);
        currentYear.setTitle("当年");
        payTypeStatisticsDao.fill(currentYear, currentYearMinDateCreated, currentYearMaxDateCreated);

        List<Achievement> list = new ArrayList<Achievement>();
        list.add(beforeYesterday);
        list.add(yesterday);
        list.add(today);
        list.add(lastMonth);
        list.add(currentMonth);
        list.add(lastYear);
        list.add(currentYear);

        return list;
    }
}
