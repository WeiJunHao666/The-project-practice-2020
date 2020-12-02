package com.erhuo.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DateTypeHandler extends BaseTypeHandler<Date> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        long time = parameter.getTime();
        ps.setLong(i,time);
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long aLong = rs.getLong(columnName);
        Date date = new Date(aLong);
        System.out.println(date+"asdasfasfasfasf");
        return date;
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long aLong = rs.getLong(columnIndex);
        Date date = new Date(aLong);
        System.out.println(date+"asdasfasfasfasf");
        return date;
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long aLong = cs.getLong(columnIndex);
        Date date = new Date(aLong);
        System.out.println(date+"asdasfasfasfasf");
        return date;
    }
}
