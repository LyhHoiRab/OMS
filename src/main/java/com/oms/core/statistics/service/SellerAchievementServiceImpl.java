package com.oms.core.statistics.service;

import com.oms.commons.utils.ExcelUtils;
import com.oms.core.profile.dao.ProfileDao;
import com.oms.core.profile.entity.Profile;
import com.oms.core.statistics.dao.PayTypeStatisticsDao;
import com.oms.core.statistics.dao.SellerAchievementDao;
import com.oms.core.statistics.entity.PayTypeStatistics;
import com.oms.core.statistics.entity.SellerAchievement;
import com.oms.core.trade.dao.WechatInfoDao;
import com.oms.core.trade.entity.WechatInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wah.doraemon.utils.ObjectUtils;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class SellerAchievementServiceImpl implements SellerAchievementService{

    @Autowired
    private SellerAchievementDao sellerAchievementDao;

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private WechatInfoDao wechatInfoDao;

    @Autowired
    private PayTypeStatisticsDao payTypeStatisticsDao;

    @Override
    public List<SellerAchievement> find(String sellerName, Date minDateCreated, Date maxDateCreated){
        //销售信息
        List<Profile> profiles = null;
        if(StringUtils.isNotBlank(sellerName)){
            profiles = profileDao.find(sellerName, null);
        }

        //微信信息
        List<WechatInfo> wechatInfos = null;

        //付款类型统计
        List<PayTypeStatistics> payTypeStatistics = null;

        //业绩列表
        List<SellerAchievement> list = sellerAchievementDao.find(minDateCreated, maxDateCreated, ObjectUtils.properties(profiles, "id", Long.class));

        if(!list.isEmpty()){
            if(profiles == null || profiles.isEmpty()){
                profiles = profileDao.findByIds(ObjectUtils.properties(list, "pUserId", Long.class));
            }

            if(wechatInfos == null || wechatInfos.isEmpty()){
                wechatInfos = wechatInfoDao.find(null, null, ObjectUtils.properties(list, "pUserId", Long.class));
            }

            if(payTypeStatistics == null || payTypeStatistics.isEmpty()){
                payTypeStatistics = payTypeStatisticsDao.findGroupBySeller(null, minDateCreated, maxDateCreated, ObjectUtils.properties(list, "pUserId", Long.class));
            }

            for(SellerAchievement achievement : list){
                //填充销售信息
                for(Profile profile : profiles){
                    if(profile.getId().equals(achievement.getPUserId())){
                        achievement.setSeller(profile);
                        break;
                    }
                }

                //填充微信信息
                for(WechatInfo wechatInfo : wechatInfos){
                    if(wechatInfo.getUserId().equals(achievement.getPUserId())){
                        List<WechatInfo> wechats = achievement.getWechatInfos();
                        if(wechats == null){
                            wechats = new ArrayList<WechatInfo>();
                            achievement.setWechatInfos(wechats);
                        }

                        wechats.add(wechatInfo);
                    }
                }

                //填充付款类型统计信息
                for(PayTypeStatistics statistics : payTypeStatistics){
                    if(statistics.getPUserId().equals(achievement.getPUserId())){
                        switch(statistics.getPayType()){
                            case OFFLINE_CASH:
                                achievement.setCashStatistics(statistics);
                                break;
                            case OFFLINE_ALIPAY:
                                achievement.setAlipayStatistics(statistics);
                                break;
                            case OFFLINE_ALIPAY_PREPAID:
                                achievement.setAbcpayPrepaidStatistics(statistics);
                                break;
                            case OFFLINE_WXPAY:
                                achievement.setWxpayStatistics(statistics);
                                break;
                            case OFFLINE_WXPAY_PREPAID:
                                achievement.setWxpayPrepaidStatistics(statistics);
                                break;
                            case OFFLINE_WXQRCODEPAY:
                                achievement.setWxqrcodepayStatistics(statistics);
                                break;
                            case OFFLINE_WXQRCODEPAY_PREPAID:
                                achievement.setWxqrcodepayPrepaidStatistics(statistics);
                                break;
                            case OFFLINE_PSBC_PAY:
                                achievement.setPsbcpayStatistics(statistics);
                                break;
                            case OFFLINE_PSBC_PREPAID:
                                achievement.setPsbcpayPrepaidStatistics(statistics);
                                break;
                            case OFFLINE_ABC_PAY:
                                achievement.setAbcpayStatistics(statistics);
                                break;
                            case OFFLINE_ABC_PREPAID:
                                achievement.setAbcpayPrepaidStatistics(statistics);
                                break;
                            default:
                                achievement.setUnknownStatistics(statistics);
                                break;
                        }
                    }
                }
            }
        }

        return list;
    }

    @Override
    public XSSFWorkbook export(String sellerName, Date minDateCreated, Date maxDateCreated){
        //销售信息
        List<Profile> profiles = null;
        if(StringUtils.isNotBlank(sellerName)){
            profiles = profileDao.find(sellerName, null);
        }

        //微信信息
        List<WechatInfo> wechatInfos = null;

        //付款类型统计
        List<PayTypeStatistics> payTypeStatistics = null;

        //业绩列表
        List<SellerAchievement> list = sellerAchievementDao.find(minDateCreated, maxDateCreated, ObjectUtils.properties(profiles, "id", Long.class));

        if(!list.isEmpty()){
            if(profiles == null || profiles.isEmpty()){
                profiles = profileDao.findByIds(ObjectUtils.properties(list, "pUserId", Long.class));
            }

            if(wechatInfos == null || wechatInfos.isEmpty()){
                wechatInfos = wechatInfoDao.find(null, null, ObjectUtils.properties(list, "pUserId", Long.class));
            }

            if(payTypeStatistics == null || payTypeStatistics.isEmpty()){
                payTypeStatistics = payTypeStatisticsDao.findGroupBySeller(null, minDateCreated, maxDateCreated, ObjectUtils.properties(list, "pUserId", Long.class));
            }

            for(SellerAchievement achievement : list){
                //填充销售信息
                for(Profile profile : profiles){
                    if(profile.getId().equals(achievement.getPUserId())){
                        achievement.setSeller(profile);
                        break;
                    }
                }

                //填充微信信息
                for(WechatInfo wechatInfo : wechatInfos){
                    if(wechatInfo.getUserId().equals(achievement.getPUserId())){
                        List<WechatInfo> wechats = achievement.getWechatInfos();
                        if(wechats == null){
                            wechats = new ArrayList<WechatInfo>();
                            achievement.setWechatInfos(wechats);
                        }

                        wechats.add(wechatInfo);
                    }
                }

                //填充付款类型统计信息
                for(PayTypeStatistics statistics : payTypeStatistics){
                    if(statistics.getPUserId().equals(achievement.getPUserId())){
                        switch(statistics.getPayType()){
                            case OFFLINE_CASH:
                                achievement.setCashStatistics(statistics);
                                break;
                            case OFFLINE_ALIPAY:
                                achievement.setAlipayStatistics(statistics);
                                break;
                            case OFFLINE_ALIPAY_PREPAID:
                                achievement.setAbcpayPrepaidStatistics(statistics);
                                break;
                            case OFFLINE_WXPAY:
                                achievement.setWxpayStatistics(statistics);
                                break;
                            case OFFLINE_WXPAY_PREPAID:
                                achievement.setWxpayPrepaidStatistics(statistics);
                                break;
                            case OFFLINE_WXQRCODEPAY:
                                achievement.setWxqrcodepayStatistics(statistics);
                                break;
                            case OFFLINE_WXQRCODEPAY_PREPAID:
                                achievement.setWxqrcodepayPrepaidStatistics(statistics);
                                break;
                            case OFFLINE_PSBC_PAY:
                                achievement.setPsbcpayStatistics(statistics);
                                break;
                            case OFFLINE_PSBC_PREPAID:
                                achievement.setPsbcpayPrepaidStatistics(statistics);
                                break;
                            case OFFLINE_ABC_PAY:
                                achievement.setAbcpayStatistics(statistics);
                                break;
                            case OFFLINE_ABC_PREPAID:
                                achievement.setAbcpayPrepaidStatistics(statistics);
                                break;
                            default:
                                achievement.setUnknownStatistics(statistics);
                                break;
                        }
                    }
                }
            }
        }

        //Excel内容
        Map<String, String> titles = new LinkedHashMap<String, String>();
        titles.put("销售人员", "seller.realName");
        titles.put("微信号", "wechatInfos.wxNo");
        titles.put("销售总额(元)", "price");
        titles.put("微信收款(元)", "wxpayPrice");
        titles.put("企业微信收款(元)", "wxqrcodepayPrice");
        titles.put("支付宝收款(元)", "alipayPrice");
        titles.put("邮政银行收款(元)", "psbcpayPrice");
        titles.put("农业银行收款(元)", "abcpayPrice");
        titles.put("实际代收(元)", "collectPrice");
        titles.put("订单总数", "total");

        return ExcelUtils.write(titles, list, ",");
    }
}
