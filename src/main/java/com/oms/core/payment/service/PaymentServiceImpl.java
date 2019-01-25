package com.oms.core.payment.service;

import com.oms.core.payment.dao.PaymentDao;
import com.oms.core.payment.entity.Payment;
import com.wah.doraemon.domain.consts.UsingStatus;
import com.wah.doraemon.utils.Page;
import com.wah.doraemon.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentDao paymentDao;

    @Transactional
    @Override
    public void save(Payment payment){
        Assert.notNull(payment, "付款方式信息不能为空");

        paymentDao.saveOrUpdate(payment);
    }

    @Transactional
    @Override
    public void update(Payment payment){
        Assert.notNull(payment, "付款方式信息不能为空");
        Assert.hasText(payment.getId(), "付款方式ID不能为空");

        paymentDao.saveOrUpdate(payment);
    }

    @Override
    public Payment getId(String id){
        Assert.hasText(id, "付款方式ID不能为空");

        return paymentDao.getById(id);
    }

    @Override
    public Page<Payment> page(Long pageNum, Long pageSize, String description, UsingStatus status){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);

        return paymentDao.page(pageRequest, description, status);
    }
}
