package com.mungdori.mun.utils.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class StringListTypeHandler extends BaseTypeHandler<List<String>> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        try {
            String json = objectMapper.writeValueAsString(parameter);
            ps.setString(i, json);
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting List<String> to JSON string", e);
        }
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return parseString(columnValue);
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return parseString(columnValue);
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return parseString(columnValue);
    }

    private List<String> parseString(String input) throws SQLException {
        try {
            if (input == null || input.isEmpty()) {
                return Collections.emptyList(); // 또는 null을 반환하도록 변경 가능
            }
            return objectMapper.readValue(input, List.class);
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting JSON string to List<String>", e);
        }
    }
}
