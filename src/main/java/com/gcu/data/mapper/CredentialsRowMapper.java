package com.gcu.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.model.CredentialsModel;

/**
 * Creates the CredentialRowMapper
 * @author connorrolstad
 *
 */
public class CredentialsRowMapper implements RowMapper<CredentialsModel> {
    
    @Override
    public CredentialsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        CredentialsModel credentials = new CredentialsModel();
//      maps credentials to returned sql data
        credentials.setUsername(rs.getString("username"));
        credentials.setPassword(rs.getString("password"));

        return credentials;
    }
    
}
