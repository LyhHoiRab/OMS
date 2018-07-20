package com.oms.commons.security.handler;

import com.oms.commons.utils.editor.DateEditor;
import com.oms.commons.utils.editor.ExpressEditor;
import com.oms.core.trade.consts.ExpressType;
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
    public void initExpressBinder(WebDataBinder binder){
        binder.registerCustomEditor(ExpressType.class, new ExpressEditor());
    }
}
