package com.oms.commons.utils.editor;

import com.oms.core.trade.consts.ExpressType;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

public class ExpressEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if(StringUtils.isNotBlank(text)){
            setValue(ExpressType.getById(Integer.parseInt(text)));
        }
    }
}
