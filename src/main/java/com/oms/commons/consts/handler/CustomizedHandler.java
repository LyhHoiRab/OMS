package com.oms.commons.consts.handler;

import com.oms.commons.consts.Customized;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomizedHandler extends BaseTypeHandler<Customized> implements TypeHandler<Customized>{

    public void setNonNullParameter(PreparedStatement ps, int i, Customized parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public Customized getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return Customized.getById(rs.getInt(columnName));
    }

    public Customized getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return Customized.getById(rs.getInt(columnIndex));
    }

    public Customized getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return Customized.getById(cs.getInt(columnIndex));
    }
}
