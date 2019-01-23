package com.oms.commons.consts.handler;

import com.oms.commons.consts.ProductType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTypeHandler extends BaseTypeHandler<ProductType> implements TypeHandler<ProductType>{

    public void setNonNullParameter(PreparedStatement ps, int i, ProductType parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public ProductType getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return ProductType.getById(rs.getInt(columnName));
    }

    public ProductType getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return ProductType.getById(rs.getInt(columnIndex));
    }

    public ProductType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return ProductType.getById(cs.getInt(columnIndex));
    }
}
