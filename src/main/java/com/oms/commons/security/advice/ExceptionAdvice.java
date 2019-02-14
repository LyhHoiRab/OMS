package com.oms.commons.security.advice;

import com.oms.commons.security.exception.AccountNotFoundException;
import com.oms.commons.security.exception.AccountStatusException;
import com.oms.commons.security.exception.NotLoginStatusException;
import com.oms.commons.security.exception.UpyunException;
import com.wah.doraemon.security.exception.DataNotFoundException;
import com.wah.doraemon.security.response.Responsed;
import com.wah.doraemon.security.response.consts.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionAdvice{

    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(value = AccountNotFoundException.class)
    public Responsed accountNotFount(AccountNotFoundException e){
        logger.error(e.getMessage(), e);

        return new Responsed(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR, false);
    }

    @ExceptionHandler(value = AccountStatusException.class)
    public Responsed accountStatus(AccountStatusException e){
        logger.error(e.getMessage(), e);

        return new Responsed(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR, false);
    }

    @ExceptionHandler(value = NotLoginStatusException.class)
    public Responsed notLoginStatus(NotLoginStatusException e){
        logger.error(e.getMessage(), e);

        return new Responsed(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR, false);
    }

    @ExceptionHandler(value = UpyunException.class)
    public Responsed upload(UpyunException e){
        logger.error(e.getMessage(), e);

        return new Responsed(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR, false);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public Responsed dataNotFound(DataNotFoundException e){
        logger.error(e.getMessage(), e);

        return new Responsed(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR, false);
    }

    @ExceptionHandler(value = Exception.class)
    public Responsed exception(Exception e){
        logger.error(e.getMessage(), e);

        return new Responsed(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR, false);
    }
}
