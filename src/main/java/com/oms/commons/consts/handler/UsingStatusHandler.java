package com.oms.commons.consts.handler;

import com.wah.doraemon.domain.consts.UsingStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsingStatusHandler extends BaseTypeHandler<UsingStatus> implements TypeHandler<UsingStatus>{

    public void setNonNullParameter(PreparedStatement ps, int i, UsingStatus parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public UsingStatus getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return UsingStatus.getById(rs.getInt(columnName));
    }

    public UsingStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return UsingStatus.getById(rs.getInt(columnIndex));
    }

    public UsingStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return UsingStatus.getById(cs.getInt(columnIndex));
    }
}
