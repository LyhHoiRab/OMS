package com.oms.commons.consts.editor;

import com.wah.doraemon.domain.consts.UsingStatus;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

public class UsingStatusEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if(StringUtils.isNotBlank(text)){
            int id = Integer.parseInt(text);

            setValue(UsingStatus.getById(id));
        }
    }
}
