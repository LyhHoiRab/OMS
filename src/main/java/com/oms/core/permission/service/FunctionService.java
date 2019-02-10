package com.oms.core.permission.service;

import com.oms.core.permission.entity.Function;
import com.wah.doraemon.utils.Page;

public interface FunctionService{

    void sync();

    Page<Function> page(Long pageNum, Long pageSize, String api, String method, Boolean allocatable, Boolean granted, Boolean cookie);
}
