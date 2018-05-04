package com.lab.core.trade.utils.handler;

import com.lab.core.trade.consts.StatusType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusTypeHandler extends BaseTypeHandler<StatusType> implements TypeHandler<StatusType>{

    public StatusTypeHandler(){
    }

    public void setNonNullParameter(PreparedStatement ps, int i, StatusType parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public StatusType getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return StatusType.getById(rs.getInt(columnName));
    }

    public StatusType getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return StatusType.getById(rs.getInt(columnIndex));
    }

    public StatusType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return StatusType.getById(cs.getInt(columnIndex));
    }
}
