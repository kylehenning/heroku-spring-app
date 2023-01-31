package com.gcu.business;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gcu.model.UserModel;

/**
 * Interface class used for user business service
 * @author kyleb
 *
 */
public interface UserBusinessServiceInterface extends UserDetailsService {
    /**
     * gets the users from the user list
     * @return users list
     */
    public List<UserModel> getAllUsers();
    /**
     * gets single user by id
     * @param id passed in
     * @return users list
     */
    public UserModel getUser(int id);
    /**
     * creates a user based on the usermodel passed in and then adds the user to the user list
     * @param userModel passed in
     * @return the userModel that was added to the user list
     */
	public UserModel createUser(UserModel userModel);
    /**
     * User service init method
     */
	public void init();
    /**
     * User service destroy method
     */
	public void destroy();
}
