package com.oms.commons.security.advice;

import com.oms.commons.consts.AccountStatus;
import com.oms.commons.consts.Customized;
import com.oms.commons.consts.ProductType;
import com.oms.commons.consts.editor.*;
import com.wah.doraemon.domain.consts.UsingStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;

@ControllerAdvice
public class EditorAdvice{

    @InitBinder
    public void initDateBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @InitBinder
    public void initProductTypeBinder(WebDataBinder binder){
        binder.registerCustomEditor(ProductType.class, new ProductTypeEditor());
    }

    @InitBinder
    public void initCustomizedBinder(WebDataBinder binder){
        binder.registerCustomEditor(Customized.class, new CustomizedEditor());
    }

    @InitBinder
    public void initUsingStatusBinder(WebDataBinder binder){
        binder.registerCustomEditor(UsingStatus.class, new UsingStatusEditor());
    }

    @InitBinder
    public void initAccountStatusBinder(WebDataBinder binder){
        binder.registerCustomEditor(AccountStatus.class, new AccountStatusEditor());
    }
}
