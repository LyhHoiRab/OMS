package com.oms.commons.security.advice.editor;

import com.oms.commons.consts.Customized;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

public class CustomizedEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if(StringUtils.isNotBlank(text)){
            int id = Integer.parseInt(text);

            setValue(Customized.getById(id));
        }
    }
}
