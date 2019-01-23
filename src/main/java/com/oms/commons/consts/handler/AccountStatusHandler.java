package com.oms.commons.consts.handler;

import com.oms.commons.consts.AccountStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountStatusHandler extends BaseTypeHandler<AccountStatus> implements TypeHandler<AccountStatus>{

    public void setNonNullParameter(PreparedStatement ps, int i, AccountStatus parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public AccountStatus getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return AccountStatus.getById(rs.getInt(columnName));
    }

    public AccountStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return AccountStatus.getById(rs.getInt(columnIndex));
    }

    public AccountStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return AccountStatus.getById(cs.getInt(columnIndex));
    }
}
