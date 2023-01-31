package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.data.mapper.CredentialsRowMapper;
import com.gcu.model.CredentialsModel;
import com.gcu.util.DatabaseException;

/**
 * Creates the CredentialsDataService
 * @author connorrolstad
 *
 */
@Service
public class CredentialsDataService implements DataAccessInterface<CredentialsModel> {

	/**
	 * This contains how to talk to the mysql server
	 */
    @SuppressWarnings("unused")
    @Autowired
    private DataSource dataSource;
    /**
     * 
	 * This is used to execute the sql statements on the datasource
     */
    private JdbcTemplate jdbcTemplateObject;
    
    /**
     * Non-Default Constructor for the CDS
     * @param dataSource passed in
     */
    public CredentialsDataService(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    /**
     * gets all the credentials from the credentials table
     * @return list of credentials
     * @throws DatabaseException something went wrong with the database
     */
    @Override
    public List<CredentialsModel> findAll() {
//      Select all credentials from the credentials table
        String sql = "SELECT * FROM credentials";
        List<CredentialsModel> credentials = new ArrayList<CredentialsModel>();
        
//      handle exceptions if db issues
        try
        {
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
            while(srs.next())
            {
                credentials.add(new CredentialsModel(srs.getString(2),//Username
                                            srs.getString(3),//Password
                                            srs.getInt(1)//user_Id
                                            ));
            }
        }
        catch (Exception e)
        {
        	throw new DatabaseException(e, "Database Error");
        }
        return credentials;
    }

    /**
     * finds the specific Credentials by the id passed in
     * @return a single CredentialsModel
     * @param id passed in
     * @throws DatabaseException something went wrong with the database
     */
    @Override
    public CredentialsModel findById(int id) {
        String sql = "SELECT * FROM credentials WHERE credentials_id = ?";
        CredentialsModel credentials = new CredentialsModel();
        
//      Handle exceptions if db issues
        try
        {
//          user credentials row mapper to get map return values to credentials model and pass in ID to search by
            credentials = jdbcTemplateObject.queryForObject(sql, new CredentialsRowMapper(), id);
        }
        catch (Exception e)
        {
        	throw new DatabaseException(e, "Database Error");
        }
        return credentials;
    }
    
    /**
     * finds the credentials based on the username passed in
     * @param username passed in
     * @throws DatabaseException something went wrong with the database
     * @return a single CredentialsModel
     */
    public CredentialsModel findByUsername(String username) {
    	String sql = "SELECT * FROM credentials WHERE username = ?";
    	CredentialsModel credentials = new CredentialsModel();
    	
    	try {
    		// map rows based off of results from sql with passed in params
    		credentials = jdbcTemplateObject.queryForObject(sql, new CredentialsRowMapper(), username);
    	} catch (Exception e) {
    		throw new DatabaseException(e, "Database Error");
    	}
    	
    	return credentials;
    }

    /**
     * creates a new credentials entry into credentials db
     * @param credentials passed in
     * @throws DatabaseException something went wrong with the database
     * @return rows number of rows changed (should be 1)
     */
    @Override
    public int create(CredentialsModel credentials) {
        String sql = "INSERT INTO credentials(username, password, user_id) VALUES(?, ?, ?)";
        int rows = -1;
        try
        {
            rows = jdbcTemplateObject.update(sql,
                                             credentials.getUsername(),
                                             new BCryptPasswordEncoder().encode(credentials.getPassword()),
                                             credentials.getUserId()
                                             );
        }
        catch (Exception e)
        {
        	throw new DatabaseException(e, "Database Error");
        }
        return rows;
    }

    /**
     * Update will update the credentials (M5)
     * @param credentialsModel passed in
     * @return int updated or not
     */
    @Override
    public int update(CredentialsModel credentialsModel) {
        return 0;
    }

    /**
     * Delete will delete Credentials (M6)
     * @param id passed in
     * @return boolean deleted or not
     */
    @Override
    public boolean delete(int id) {
        return false;
    }

}
