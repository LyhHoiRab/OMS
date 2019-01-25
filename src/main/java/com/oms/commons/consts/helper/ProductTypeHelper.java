package com.oms.commons.consts.helper;

import com.oms.commons.consts.ProductType;
import com.wah.mybatis.helper.domain.TypeHelper;

public class ProductTypeHelper implements TypeHelper<ProductType>{

    @Override
    public Object getNonNullParameter(ProductType type){
        return type.getId();
    }
}
