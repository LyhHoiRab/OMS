package com.oms.core.express.utils;

import com.oms.commons.consts.CompanyInfo;
import com.oms.commons.utils.ExcelUtils;
import com.oms.core.express.entity.YTDetail;
import com.oms.core.product.entity.BoxProduct;
import com.oms.core.trade.consts.ExpressType;
import com.oms.core.trade.entity.Logistics;
import com.oms.core.trade.entity.Trade;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class YTExpressUtils{

    public List<YTDetail> parse(List<Trade> trades){
        if(trades == null || trades.isEmpty()){
            return null;
        }

        List<YTDetail> list = new ArrayList<YTDetail>();

        for(Trade trade : trades){
            if(ExpressType.YUANTONG.equals(trade.getExpressType())){
                YTDetail detail = new YTDetail();
                //填充模版信息
                //订单号
                detail.setTradeId(trade.getTradeId());

                //商品名称
                List<BoxProduct> products = trade.getProducts();
                String productName = ExcelUtils.getValue(products, "products.product.prodName", ",").toString();
                detail.setProductName(productName);

                //数量
                Integer count = 0;
                for(BoxProduct product : products){
                    count += product.getNum();
                }
                detail.setCount(count);

                //物流信息
                Logistics logistics = trade.getLogistics();
                //买家姓名
                detail.setContactName(logistics.getContactName());
                //买家省份
                detail.setProvince(logistics.getProvince());
                //买家城市
                detail.setCity(logistics.getCity());
                //买家地区
                detail.setDistrict(logistics.getDistrict());
                //买家地址
                detail.setAddress(logistics.getAddrDetail());
                //买家手机
                detail.setPhone(logistics.getPhone());

                //发件人
                detail.setSenderName(CompanyInfo.SENDER_NAME);
                detail.setSenderPhone(CompanyInfo.SENDER_PHONE);
                detail.setSenderProvince(CompanyInfo.PROVINCE);
                detail.setSenderCity(CompanyInfo.CITY);
                detail.setSenderDistrict(CompanyInfo.DISTRICT);
                detail.setSenderAddress(CompanyInfo.ADDRESS);
                detail.setSenderZip(CompanyInfo.ZIP);

                //代收金额
                Integer collect = trade.getCollectFee().intValue();
                detail.setCollect(collect == 0 ? null : new Double(collect));

                list.add(detail);
            }
        }

        return list;
    }
}
