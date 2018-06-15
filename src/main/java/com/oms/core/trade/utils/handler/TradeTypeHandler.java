package com.oms.core.trade.utils.handler;

import com.oms.core.trade.consts.TradeType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TradeTypeHandler extends BaseTypeHandler<TradeType> implements TypeHandler<TradeType>{

    public TradeTypeHandler(){
    }

    public void setNonNullParameter(PreparedStatement ps, int i, TradeType parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public TradeType getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return TradeType.getById(rs.getInt(columnName));
    }

    public TradeType getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return TradeType.getById(rs.getInt(columnIndex));
    }

    public TradeType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return TradeType.getById(cs.getInt(columnIndex));
    }
}
