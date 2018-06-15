package com.oms.core.trade.utils.handler;

import com.oms.core.trade.consts.OrderStateType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStateTypeHandler extends BaseTypeHandler<OrderStateType> implements TypeHandler<OrderStateType>{

    public OrderStateTypeHandler(){
    }

    public void setNonNullParameter(PreparedStatement ps, int i, OrderStateType parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public OrderStateType getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return OrderStateType.getById(rs.getInt(columnName));
    }

    public OrderStateType getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return OrderStateType.getById(rs.getInt(columnIndex));
    }

    public OrderStateType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return OrderStateType.getById(cs.getInt(columnIndex));
    }
}
