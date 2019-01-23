package com.oms.commons.consts.helper;

import com.oms.commons.consts.Customized;
import com.wah.mybatis.helper.domain.TypeHelper;

public class CustomizedHelper implements TypeHelper<Customized>{

    public Object getNonNullParameter(Customized customized){
        return customized.getId();
    }
}
