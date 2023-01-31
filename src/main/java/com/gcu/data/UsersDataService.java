package com.gcu.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.data.mapper.UserRowMapper;
import com.gcu.model.UserModel;
import com.gcu.util.DatabaseException;

/**
 * Creates the UsersDataService
 * @author connorrolstad
 *
 */
@Service
public class UsersDataService implements DataAccessInterface<UserModel> {

	/**
	 * Deals with connecting to the database
	 */
    @SuppressWarnings("unused")
    @Autowired
    private DataSource dataSource;
    
    /**
     * Executes sql statements against the datasource
     */
    private JdbcTemplate jdbcTemplateObject;
    
    /**
     * Non-Default constructor for UDS
     * @param dataSource passed in
     */
    public UsersDataService(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    /**
     * gets all users from users table
     * @return list of users in database
     * @throws DatabaseException something went wrong with the database
     */
    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users";
        List<UserModel> users = new ArrayList<UserModel>();
        
        try
        {
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
            while(srs.next())
            {
                users.add(new UserModel(srs.getString(2),//First Name
                                            srs.getString(3),//Last Name
                                            srs.getString(4),//Email
                                            srs.getString(5),//Phonenumber
                                            null,//Credential Model
                                            srs.getInt(1)//User Id
                                            ));
            }
        }
        catch (Exception e)
        {
            throw new DatabaseException(e, "Database Error");
        }
        return users;
    }
    
    /**
     * finds user based off of id passed in
     * @param id passed in
     * @return UserModel found with id
     * @throws DatabaseException something went wrong with the database
     */
    @Override
    public UserModel findById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        UserModel user;
        
        try
        {
//          pass in id param and map rows from sql query
            user = jdbcTemplateObject.queryForObject(sql, new UserRowMapper(), id);
        }
        catch (Exception e)
        {
            throw new DatabaseException(e, "Database Error");
        }
        return user;
    }

    /**
     * Creates a user and returns the user id to be used by credentials
     * @param user UserModel passed in
     * @throws DatabaseException something went wrong with the database
     * @return user int id for credentials service to create new credentials and map to this user
     */
    @Override
    public int create(UserModel user) {
        String sql = "INSERT INTO users(first_name, last_name, email_address, phone_number) VALUES(?, ?, ?, ?)";
        try
        {
//          Create user but return a key of the auto generated user id from db
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplateObject.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                            connection.prepareStatement(sql, new String[] {"user_id"});
                            ps.setString(1, user.getFirstName());
                            ps.setString(2, user.getLastName());
                            ps.setString(3, user.getEmailAddress());
                            ps.setString(4, user.getPhoneNumber());
                        
                        return ps;
                    }
                },
                keyHolder);
//              convert key to int
                Number key = keyHolder.getKey();
                int id;
                if(key != null)
                    id = key.intValue();
                else
                    throw new RuntimeException("[UsersDataService][Create] Error assigning User Id");

            return id;
        }
        catch (Exception e)
        {
            throw new DatabaseException(e, "Database Error");
        }
    }

    /**
     * update user (M5)
     * @param userModel is a userModel passed in
     * @return int updated or not
     */
    @Override
    public int update(UserModel userModel) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * delete user (M5)
     * @param id is an id passed in
     * @return boolean updated or not
     */
    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return false;
    }
}
