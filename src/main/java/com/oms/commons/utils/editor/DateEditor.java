package com.oms.commons.utils.editor;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

public class DateEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        setValue(StringUtils.isBlank(text) ? null : text);
    }
}

