package com.gcu.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.model.UserModel;

/**
 * Creates the UserRowMapper
 * @author connorrolstad
 *
 */
public class UserRowMapper implements RowMapper<UserModel> {
    
    @Override
    public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserModel user = new UserModel();
//      maps the returned data from query to a new user model
        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmailAddress(rs.getString("email_address"));
        user.setPhoneNumber(rs.getString("phone_number"));

        return user;
    }
    
}
