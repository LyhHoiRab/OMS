package com.oms.commons.consts.helper;

import com.oms.commons.consts.AccountStatus;
import com.wah.mybatis.helper.domain.TypeHelper;

public class AccountStatusHelper implements TypeHelper<AccountStatus>{

    @Override
    public Object getNonNullParameter(AccountStatus status){
        return status.getId();
    }
}
