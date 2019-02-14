package com.oms.core.customer.webservice;

import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.customer.entity.Report;
import com.oms.core.customer.service.ReportService;
import com.wah.doraemon.security.response.Responsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/report")
public class ReportRestController{

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "新增分析报告信息")
    public Responsed save(@RequestBody Report report){
        reportService.save(report);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "修改分析报告信息")
    public Responsed update(@RequestBody Report report){
        reportService.update(report);

        return new Responsed("更新成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "根据ID查询分析报告")
    public Responsed<Report> getById(@PathVariable("id") String id){
        Report report = reportService.getById(id);

        return new Responsed<Report>("查询成功", report);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "根据客户ID查询分析报告")
    public Responsed<List<Report>> findByClientId(String clientId){
        List<Report> list = reportService.findByClientId(clientId);

        return new Responsed<List<Report>>("查询成功", list);
    }
}
