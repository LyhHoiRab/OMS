package com.lab.core.trade.utils.handler;

import com.lab.core.trade.consts.OrderStatusType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStatusTypeHandler extends BaseTypeHandler<OrderStatusType> implements TypeHandler<OrderStatusType>{

    public OrderStatusTypeHandler(){
    }

    public void setNonNullParameter(PreparedStatement ps, int i, OrderStatusType parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public OrderStatusType getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return OrderStatusType.getById(rs.getInt(columnName));
    }

    public OrderStatusType getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return OrderStatusType.getById(rs.getInt(columnIndex));
    }

    public OrderStatusType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return OrderStatusType.getById(cs.getInt(columnIndex));
    }
}
