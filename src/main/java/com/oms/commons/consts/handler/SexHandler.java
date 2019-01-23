package com.oms.commons.consts.handler;

import com.oms.commons.consts.Sex;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexHandler extends BaseTypeHandler<Sex> implements TypeHandler<Sex>{

    public void setNonNullParameter(PreparedStatement ps, int i, Sex parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public Sex getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return Sex.getById(rs.getInt(columnName));
    }

    public Sex getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return Sex.getById(rs.getInt(columnIndex));
    }

    public Sex getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return Sex.getById(cs.getInt(columnIndex));
    }
}
