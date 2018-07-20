package com.oms.core.trade.webservice;

import com.oms.core.trade.consts.ExpressType;
import com.oms.core.trade.consts.PayType;
import com.oms.core.trade.entity.Trade;
import com.oms.core.trade.service.TradeService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/1.0/trade")
public class TradeRestController{

    @Autowired
    private TradeService tradeService;

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Trade>> page(Long pageNum, Long pageSize, String wxno, String tradeId, ExpressType express, PayType payType,
                                       Long prepaidFee, Long price, Date minDateCreated, Date maxDateCreated, Date minAppointDeliveryTime,
                                       Date maxAppointDeliveryTime, String contactName, String phone, String province, String city,
                                       String district, String sellerName, String wlnumber, String prodName){

        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Trade> page = tradeService.page(pageRequest, wxno, tradeId, express, payType, prepaidFee, price, minDateCreated, maxDateCreated,
                                             minAppointDeliveryTime, maxAppointDeliveryTime, contactName, phone, province,
                                             city, district, sellerName, wlnumber, prodName);

        return new Responsed<Page<Trade>>("查询成功", page);
    }

    @RequestMapping(value = "/page/appointDeliveryTime/null", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Trade>> pageByAppointDeliveryTimeNull(Long pageNum, Long pageSize, String wxno, String tradeId,
                                                                PayType payType, String sellerName, Date minDateCreated,
                                                                Date maxDateCreated, String contactName, String phone){

        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Trade> page = tradeService.pageByAppointDeliveryTimeNull(pageRequest, wxno, tradeId, payType, sellerName,
                                                                      minDateCreated, maxDateCreated, contactName, phone);

        return new Responsed<Page<Trade>>("查询成功", page);
    }

    @RequestMapping(value = "/page/status/failAndUnusual", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Trade>> pageByStatusFailAndUnusual(Long pageNum, Long pageSize, String wxno, String tradeId,
                                                             PayType payType, String sellerName, Date minDateCreated,
                                                             Date maxDateCreated, String contactName, String phone, String wlnumber,
                                                             ExpressType express){

        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Trade> page = tradeService.pageByStatusFailAndUnusual(pageRequest, wxno, tradeId, payType, sellerName,
                                                                   minDateCreated, maxDateCreated, contactName, phone, wlnumber, express);

        return new Responsed<Page<Trade>>("查询成功", page);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void export(HttpServletResponse response, String wxno, String tradeId, ExpressType express, PayType payType,
                       Long prepaidFee, Long price, Date minDateCreated, Date maxDateCreated, Date minAppointDeliveryTime,
                       Date maxAppointDeliveryTime, String contactName, String phone, String province, String city,
                       String district, String sellerName, String wlnumber, String prodName) throws Exception{

        XSSFWorkbook book = tradeService.export(wxno, tradeId, express, payType, prepaidFee, price, minDateCreated,
                maxDateCreated, minAppointDeliveryTime, maxAppointDeliveryTime, contactName,
                phone, province, city, district, sellerName, wlnumber, prodName);

        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("订单记录.xlsx", "UTF-8"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        OutputStream outputStream = response.getOutputStream();
        BufferedOutputStream buffered = new BufferedOutputStream(outputStream);
        buffered.flush();

        book.write(buffered);
        buffered.close();
    }

    @RequestMapping(value = "/export/appointDeliveryTime/null", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void exportByAppointDeliveryTimeNull(HttpServletResponse response, String wxno, String tradeId,
                                                PayType payType, String sellerName, Date minDateCreated,
                                                Date maxDateCreated, String contactName, String phone) throws Exception{

        XSSFWorkbook book = tradeService.exportByAppointDeliveryTimeNull(wxno, tradeId, payType, sellerName, minDateCreated,
                                                                         maxDateCreated, contactName, phone);

        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("等通知发货订单记录.xlsx", "UTF-8"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        OutputStream outputStream = response.getOutputStream();
        BufferedOutputStream buffered = new BufferedOutputStream(outputStream);
        buffered.flush();

        book.write(buffered);
        buffered.close();
    }

    @RequestMapping(value = "/export/status/failAndUnusual", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void exportByStatusFailAndUnusual(HttpServletResponse response, String wxno, String tradeId, PayType payType,
                                             String sellerName, Date minDateCreated, Date maxDateCreated, String contactName,
                                             String phone, String wlnumber, ExpressType express) throws Exception{

        XSSFWorkbook book = tradeService.exportByStatusFailAndUnusual(wxno, tradeId, payType, sellerName, minDateCreated,
                                                                      maxDateCreated, contactName, phone, wlnumber, express);

        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("失败订单记录.xlsx", "UTF-8"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        OutputStream outputStream = response.getOutputStream();
        BufferedOutputStream buffered = new BufferedOutputStream(outputStream);
        buffered.flush();

        book.write(buffered);
        buffered.close();
    }
}
