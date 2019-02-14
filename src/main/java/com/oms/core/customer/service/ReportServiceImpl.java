package com.oms.core.customer.service;

import com.oms.core.customer.dao.ReportDao;
import com.oms.core.customer.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService{

    @Autowired
    private ReportDao reportDao;

    @Transactional
    @Override
    public void save(Report report){
        Assert.notNull(report, "分析报告信息不能为空");
        Assert.hasText(report.getClientId(), "客户ID不能为空");

        reportDao.saveOrUpdate(report);
    }

    @Transactional
    @Override
    public void update(Report report){
        Assert.notNull(report, "分析报告信息不能为空");
        Assert.hasText(report.getId(), "分析报告ID不能为空");

        reportDao.saveOrUpdate(report);
    }

    @Override
    public Report getById(String id){
        Assert.hasText(id, "分析报告ID不能为空");

        return reportDao.getById(id);
    }

    @Override
    public List<Report> findByClientId(String clientId){
        Assert.hasText(clientId, "客户ID不能为空");

        return reportDao.findByClientId(clientId);
    }
}
