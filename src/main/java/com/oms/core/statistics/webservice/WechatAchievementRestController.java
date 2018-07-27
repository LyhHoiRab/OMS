package com.oms.core.statistics.webservice;

import com.oms.commons.consts.CompanyInfo;
import com.oms.core.statistics.entity.WechatAchievement;
import com.oms.core.statistics.service.WechatAchievementService;
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
@RequestMapping(value = "/api/1.0/achievement/wechat")
public class WechatAchievementRestController{

    @Autowired
    private WechatAchievementService wechatAchievementService;

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<WechatAchievement>> find(String sellerName, String wxNo, Date minDateCreated, Date maxDateCreated){
        List<WechatAchievement> list = wechatAchievementService.find(sellerName, wxNo, minDateCreated, maxDateCreated);

        return new Responsed<List<WechatAchievement>>("查询成功", list);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void export(HttpServletResponse response, String sellerName, String wxNo, Date minDateCreated, Date maxDateCreated) throws Exception{

        XSSFWorkbook book = wechatAchievementService.export(sellerName, wxNo, minDateCreated, maxDateCreated);

        String fileName = MessageFormat.format("{0}-微信号业绩统计表.xlsx", CompanyInfo.COMPANY_NAME);

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
