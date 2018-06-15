package com.oms.core.profile.dao.mapper;

import com.oms.core.profile.entity.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface ProfileMapper{

    List<Profile> find(@Param("params") Criteria criteria);
}
