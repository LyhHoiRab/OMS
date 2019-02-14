package com.oms.core.customer.service;

import com.oms.core.customer.entity.Report;

import java.util.List;

public interface ReportService{

    void save(Report report);

    void update(Report report);

    Report getById(String id);

    List<Report> findByClientId(String clientId);
}
