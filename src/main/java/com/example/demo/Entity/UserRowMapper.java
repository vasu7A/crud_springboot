package com.example.demo.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class UserRowMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new User(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("age"),
            rs.getInt("mobile")
        );
    }
}
