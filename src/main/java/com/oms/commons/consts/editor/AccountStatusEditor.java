package com.oms.commons.consts.editor;

import com.oms.commons.consts.AccountStatus;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

public class AccountStatusEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if(StringUtils.isNotBlank(text)){
            int id = Integer.parseInt(text);

            setValue(AccountStatus.getById(id));
        }
    }
}
