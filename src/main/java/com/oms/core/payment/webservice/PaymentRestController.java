package com.oms.core.payment.webservice;

import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.payment.entity.Payment;
import com.oms.core.payment.service.PaymentService;
import com.wah.doraemon.domain.consts.UsingStatus;
import com.wah.doraemon.security.response.Responsed;
import com.wah.doraemon.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/1.0/payment")
public class PaymentRestController{

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "新增付款方式")
    public Responsed save(@RequestBody Payment payment){
        paymentService.save(payment);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "修改付款方式")
    public Responsed update(@RequestBody Payment payment){
        paymentService.update(payment);

        return new Responsed("修改成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "根据ID查询付款方式信息")
    public Responsed<Payment> getById(@PathVariable("id") String id){
        Payment payment = paymentService.getId(id);

        return new Responsed<Payment>("查询成功", payment);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "分页查询付款方式")
    public Responsed<Page<Payment>> page(Long pageNum, Long pageSize, String description, UsingStatus status){
        Page<Payment> page = paymentService.page(pageNum, pageSize, description, status);

        return new Responsed<Page<Payment>>("查询成功", page);
    }
}
