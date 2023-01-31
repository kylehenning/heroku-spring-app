package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gcu.data.CredentialsDataService;
import com.gcu.data.DataAccessInterface;
import com.gcu.model.CredentialsModel;
import com.gcu.model.UserModel;

/**
 * Class to handle User business service logic
 * @author kyleb
 *
 */
public class UserBusinessService implements UserBusinessServiceInterface {
    
	/**
	 * Deals with getting users from the database
	 */
    @Autowired
    DataAccessInterface<UserModel> userDataService;

    /**
     * Deals with getting Credentials from the database
     */
    @Autowired
    CredentialsDataService credentialsDataService;

    // Sample data for credentials model
    // private CredentialsModel sampleCred = new CredentialsModel("username", "password", 1);
    
    
    /**
     * gets the users from the user list
     * @return users list
     */
    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> users = userDataService.findAll();
        return users;
    }
    
    /**
     * creates a user based on the usermodel passed in and then adds the user to the user list
     * @param user represents the userModel passed in
     * @return the userModel that was added to the user list
     */
    @Override
    public UserModel createUser(UserModel user) {
        
        // set the userId equal to the result returned from the userDataService.create method        
        int userId = userDataService.create(user);

//      add the new user id onto the user
        user.setUserId(userId);
//      add the user id onto the credentials
        user.getCredentials().setUserId(userId);

//      create the credentials for the new user
        credentialsDataService.create(user.getCredentials());

        return user;
    }

    /**
     * User service init method
     */
    @Override
    public void init() {
        System.out.println("User Service init");

    }

    /**
     * User service destroy method
     */
    @Override
    public void destroy() {
        System.out.println("User Service destroy");
    }

    @Override
    public UserModel getUser(int id) {
        UserModel user = userDataService.findById(id);
        return user;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// finds the user that matches the username provided in the database
        CredentialsModel user = credentialsDataService.findByUsername(username);
		
        // If this user has been found than give them the USER role, granting them access to the rest of the application.
		if(user != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("USER"));
			return new User(user.getUsername(), user.getPassword(), authorities);
		} else {
			throw new UsernameNotFoundException("User with that username not found in database");
		}
	}

}
