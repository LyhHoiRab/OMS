package com.oms.core.trade.service;

import com.oms.core.product.dao.BoxProductDao;
import com.oms.core.product.entity.BoxProduct;
import com.oms.core.profile.dao.ProfileDao;
import com.oms.core.profile.entity.Profile;
import com.oms.core.trade.consts.ExpressType;
import com.oms.core.trade.consts.PayType;
import com.oms.core.trade.dao.LogisticsDao;
import com.oms.core.trade.dao.OrderDao;
import com.oms.core.trade.dao.TradeDao;
import com.oms.core.trade.dao.WechatInfoDao;
import com.oms.core.trade.entity.Logistics;
import com.oms.core.trade.entity.Order;
import com.oms.core.trade.entity.Trade;
import com.oms.core.trade.entity.WechatInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService{

    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private LogisticsDao logisticsDao;

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private WechatInfoDao wechatInfoDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private BoxProductDao boxProductDao;

    @Override
    public Page<Trade> page(PageRequest pageRequest, String wxno, String tradeId, ExpressType express, PayType payType,
                            Long prepaidFee, Long price, Date minDateCreated, Date maxDateCreated, Date minAppointDeliveryTime,
                            Date maxAppointDeliveryTime, String contactName, String phone, String province, String city,
                            String district, String sellerName, String wlnumber, String prodName){

        Assert.notNull(pageRequest, "分页信息不能为空");

        //物流信息
        List<Logistics> logisticses = null;
        if(StringUtils.isNotBlank(contactName) ||
           StringUtils.isNotBlank(phone) ||
           StringUtils.isNotBlank(province) ||
           StringUtils.isNotBlank(city) ||
           StringUtils.isNotBlank(district)){

            logisticses = logisticsDao.find(province, city, district, contactName, phone, null);
        }

        //销售信息
        List<Profile> profiles = null;
        if(StringUtils.isNotBlank(sellerName)){
            profiles = profileDao.find(sellerName, null);
        }

        //微信信息
        List<WechatInfo> wechatInfos = null;

        //快递信息
        List<Order> orders = null;
        if(StringUtils.isNotBlank(wlnumber)){
            orders = orderDao.find(null, wlnumber);
        }

        //产品列表
        List<BoxProduct> products = null;
        if(StringUtils.isNotBlank(prodName)){
            products = boxProductDao.find(null, prodName);
        }

        //订单列表
        Page<Trade> page = tradeDao.page(pageRequest, wxno, tradeId, express, payType, prepaidFee, price,
                                         minDateCreated, maxDateCreated, minAppointDeliveryTime, maxAppointDeliveryTime,
                                         ObjectUtils.properties(logisticses, "id", Long.class),
                                         ObjectUtils.properties(profiles, "id", Long.class),
                                         ObjectUtils.properties(orders, "tradeId", String.class),
                                         ObjectUtils.properties(products, "id", Long.class));

        if(page.getTotal() > 0){
            if(logisticses == null || logisticses.isEmpty()){
                logisticses = logisticsDao.findByIds(ObjectUtils.properties(page.getContent(), "consigneeId", Long.class));
            }

            if(profiles == null || logisticses.isEmpty()){
                profiles = profileDao.findByIds(ObjectUtils.properties(page.getContent(), "pUserId", Long.class));
            }

            if(wechatInfos == null || wechatInfos.isEmpty()){
                wechatInfos = wechatInfoDao.findByWxNos(ObjectUtils.properties(page.getContent(), "wxno", String.class));
            }

            if(orders == null || orders.isEmpty()){
                orders = orderDao.findByTradeIds(ObjectUtils.properties(page.getContent(), "tradeId", String.class));
            }

            if(products == null || products.isEmpty()){
                products = boxProductDao.findByBoxIds(ObjectUtils.properties(page.getContent(), "boxId", Long.class));
            }

            for(Trade trade : page.getContent()){
                //填充物流信息
                for(Logistics logistics : logisticses){
                    if(logistics.getId().equals(trade.getConsigneeId())){
                        trade.setLogistics(logistics);
                        break;
                    }
                }

                //填充销售信息
                for(Profile profile : profiles){
                    if(profile.getId().equals(trade.getPUserId())){
                        trade.setSeller(profile);
                        break;
                    }
                }

                //填充微信信息
                for(WechatInfo wechatInfo : wechatInfos){
                    if(wechatInfo.getWxNo().equalsIgnoreCase(trade.getWxno())){
                        trade.setWechatInfo(wechatInfo);
                        break;
                    }
                }

                //填充快递信息
                for(Order order : orders){
                    if(order.getTradeId().equals(trade.getTradeId())){
                        trade.setOrder(order);
                        break;
                    }
                }

                //填充产品信息
                for(BoxProduct boxProduct : products){
                    if(boxProduct.getBoxId().equals(trade.getBoxId())){
                        List<BoxProduct> box = trade.getProducts();
                        if(box == null){
                            box = new ArrayList<BoxProduct>();
                            trade.setProducts(box);
                        }

                        box.add(boxProduct);
                    }
                }
            }
        }

        return page;
    }
}
