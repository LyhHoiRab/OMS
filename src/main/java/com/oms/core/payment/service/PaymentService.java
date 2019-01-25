package com.oms.core.payment.service;

import com.oms.core.payment.entity.Payment;
import com.wah.doraemon.domain.consts.UsingStatus;
import com.wah.doraemon.utils.Page;

public interface PaymentService{

    void save(Payment payment);

    void update(Payment payment);

    Payment getId(String id);

    Page<Payment> page(Long pageNum, Long pageSize, String description, UsingStatus status);
}
