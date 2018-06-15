package com.oms.core.trade.utils.handler;

import com.oms.core.trade.consts.ExpressType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpressTypeHandler extends BaseTypeHandler<ExpressType> implements TypeHandler<ExpressType>{

    public ExpressTypeHandler(){
    }

    public void setNonNullParameter(PreparedStatement ps, int i, ExpressType parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public ExpressType getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return ExpressType.getById(rs.getInt(columnName));
    }

    public ExpressType getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return ExpressType.getById(rs.getInt(columnIndex));
    }

    public ExpressType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return ExpressType.getById(cs.getInt(columnIndex));
    }
}
