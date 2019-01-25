package com.oms.commons.consts.helper;

import com.wah.doraemon.domain.consts.UsingStatus;
import com.wah.mybatis.helper.domain.TypeHelper;

public class UsingStatusHelper implements TypeHelper<UsingStatus>{

    @Override
    public Object getNonNullParameter(UsingStatus status){
        return status.getId();
    }
}
