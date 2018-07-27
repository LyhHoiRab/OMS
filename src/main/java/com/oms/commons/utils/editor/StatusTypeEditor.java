package com.oms.commons.utils.editor;

import com.oms.core.trade.consts.StatusType;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

public class StatusTypeEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if(StringUtils.isNotBlank(text)){
            setValue(StatusType.getById(Integer.parseInt(text)));
        }
    }
}
