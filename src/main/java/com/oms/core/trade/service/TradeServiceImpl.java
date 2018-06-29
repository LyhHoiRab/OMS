package com.oms.core.trade.service;

import com.oms.commons.utils.ExcelUtils;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.ObjectUtils;

import java.util.*;

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

        if(!page.getContent().isEmpty()){
            if(logisticses == null || logisticses.isEmpty()){
                logisticses = logisticsDao.findByIds(ObjectUtils.properties(page.getContent(), "consigneeId", Long.class));
            }

            if(profiles == null || profiles.isEmpty()){
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

    @Override
    public Page<Trade> pageByAppointDeliveryTimeNull(PageRequest pageRequest, String wxno, String tradeId, PayType payType,
                                                     String sellerName, Date minDateCreated, Date maxDateCreated, String contactName,
                                                     String phone){
        Assert.notNull(pageRequest, "分页信息不能为空");

        //物流信息
        List<Logistics> logisticses = null;
        if(StringUtils.isNotBlank(contactName) ||
           StringUtils.isNotBlank(phone)){

            logisticses = logisticsDao.find(null, null, null, contactName, phone, null);
        }

        //销售信息
        List<Profile> profiles = null;
        if(StringUtils.isNotBlank(sellerName)){
            profiles = profileDao.find(sellerName, null);
        }

        //微信信息
        List<WechatInfo> wechatInfos = null;

        //产品列表
        List<BoxProduct> products = null;

        //订单列表
        Page<Trade> page = tradeDao.pageByAppointDeliveryTimeNull(pageRequest, wxno, tradeId,
                                                                  ObjectUtils.properties(profiles, "id", Long.class),
                                                                  minDateCreated, maxDateCreated);

        if(!page.getContent().isEmpty()){
            if(logisticses == null || logisticses.isEmpty()){
                logisticses = logisticsDao.findByIds(ObjectUtils.properties(page.getContent(), "consigneeId", Long.class));
            }

            if(profiles == null || profiles.isEmpty()){
                profiles = profileDao.findByIds(ObjectUtils.properties(page.getContent(), "pUserId", Long.class));
            }

            if(wechatInfos == null || wechatInfos.isEmpty()){
                wechatInfos = wechatInfoDao.findByWxNos(ObjectUtils.properties(page.getContent(), "wxno", String.class));
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

    @Override
    public Page<Trade> pageByStatusFailAndUnusual(PageRequest pageRequest, String wxno, String tradeId, PayType payType,
                                                  String sellerName, Date minDateCreated, Date maxDateCreated, String contactName,
                                                  String phone){

        Assert.notNull(pageRequest, "分页信息不能为空");

        //物流信息
        List<Logistics> logisticses = null;
        if(StringUtils.isNotBlank(contactName) ||
                StringUtils.isNotBlank(phone)){

            logisticses = logisticsDao.find(null, null, null, contactName, phone, null);
        }

        //销售信息
        List<Profile> profiles = null;
        if(StringUtils.isNotBlank(sellerName)){
            profiles = profileDao.find(sellerName, null);
        }

        //微信信息
        List<WechatInfo> wechatInfos = null;

        //产品列表
        List<BoxProduct> products = null;

        //订单列表
        Page<Trade> page = tradeDao.pageByStatusFailAndUnusual(pageRequest, wxno, tradeId,
                                                               ObjectUtils.properties(profiles, "id", Long.class),
                                                               minDateCreated, maxDateCreated);

        if(!page.getContent().isEmpty()){
            if(logisticses == null || logisticses.isEmpty()){
                logisticses = logisticsDao.findByIds(ObjectUtils.properties(page.getContent(), "consigneeId", Long.class));
            }

            if(profiles == null || profiles.isEmpty()){
                profiles = profileDao.findByIds(ObjectUtils.properties(page.getContent(), "pUserId", Long.class));
            }

            if(wechatInfos == null || wechatInfos.isEmpty()){
                wechatInfos = wechatInfoDao.findByWxNos(ObjectUtils.properties(page.getContent(), "wxno", String.class));
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

    @Override
    public XSSFWorkbook export(String wxno, String tradeId, ExpressType express, PayType payType, Long prepaidFee, Long price,
                               Date minDateCreated, Date maxDateCreated, Date minAppointDeliveryTime, Date maxAppointDeliveryTime,
                               String contactName, String phone, String province, String city, String district, String sellerName,
                               String wlnumber, String prodName){

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

        List<Trade> list = tradeDao.find(wxno, tradeId, express, payType, prepaidFee, price,
                                         minDateCreated, maxDateCreated, minAppointDeliveryTime, maxAppointDeliveryTime,
                                         ObjectUtils.properties(logisticses, "id", Long.class),
                                         ObjectUtils.properties(profiles, "id", Long.class),
                                         ObjectUtils.properties(orders, "tradeId", String.class),
                                         ObjectUtils.properties(products, "id", Long.class));

        if(!list.isEmpty()){
            if(logisticses == null || logisticses.isEmpty()){
                logisticses = logisticsDao.findByIds(ObjectUtils.properties(list, "consigneeId", Long.class));
            }

            if(profiles == null || logisticses.isEmpty()){
                profiles = profileDao.findByIds(ObjectUtils.properties(list, "pUserId", Long.class));
            }

            if(wechatInfos == null || wechatInfos.isEmpty()){
                wechatInfos = wechatInfoDao.findByWxNos(ObjectUtils.properties(list, "wxno", String.class));
            }

            if(orders == null || orders.isEmpty()){
                orders = orderDao.findByTradeIds(ObjectUtils.properties(list, "tradeId", String.class));
            }

            if(products == null || products.isEmpty()){
                products = boxProductDao.findByBoxIds(ObjectUtils.properties(list, "boxId", Long.class));
            }

            for(Trade trade : list){
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

        //Excel内容
        Map<String, String> titles = new LinkedHashMap<String, String>();
        titles.put("下单时间", "dateCreated");
        titles.put("发货时间", "appointDeliveryTime");
        titles.put("单号上传时间", "orderNumberTime");
        titles.put("销售人员", "seller.realName");
        titles.put("下单微信", "wechatInfo.wxNo");
        titles.put("客户姓名", "logistics.contactName");
        titles.put("联系电话", "logistics.phone");
        titles.put("省", "logistics.province");
        titles.put("市", "logistics.city");
        titles.put("区", "logistics.district");
        titles.put("地址", "logistics.addrDetail");
        titles.put("产品列表", "products.(product.prodName)(num)");
        titles.put("总金额(元)", "price");
        titles.put("实际金额(元)", "totalFee");
        titles.put("预付金额(元)", "prepaidFee");
        titles.put("代收金额(元)", "collectFee");
        titles.put("拒收金额(元)", "rejectFee");
        titles.put("订单状态", "status");
        titles.put("快递公司", "expressType");
        titles.put("快递单号", "order.wlnumber");
        titles.put("订单号", "tradeId");
        titles.put("支付方式", "payType");
        titles.put("备注", "tradeMemo");

        return ExcelUtils.write(titles, list, "x");
    }
}
