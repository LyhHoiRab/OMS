package com.oms.core.statistics.webservice;

import com.oms.commons.consts.CompanyInfo;
import com.oms.core.statistics.entity.SellerAchievement;
import com.oms.core.statistics.service.SellerAchievementService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/achievement/seller")
public class SellerAchievementRestController{

    @Autowired
    private SellerAchievementService sellerAchievementService;

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<SellerAchievement>> find(String sellerName, Date minDateCreated, Date maxDateCreated){
        List<SellerAchievement> list = sellerAchievementService.find(sellerName, minDateCreated, maxDateCreated);

        return new Responsed<List<SellerAchievement>>("查询成功", list);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void export(HttpServletResponse response, String sellerName, Date minDateCreated, Date maxDateCreated) throws Exception{

        XSSFWorkbook book = sellerAchievementService.export(sellerName, minDateCreated, maxDateCreated);

        String fileName = MessageFormat.format("{0}-销售业绩统计表.xlsx", CompanyInfo.COMPANY_NAME);

        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
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
