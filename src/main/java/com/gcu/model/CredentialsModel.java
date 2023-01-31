package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Sets up credentials model class
 * @author kyleb
 *
 */
public class CredentialsModel {
	
	/**
	 * Username of the user
	 */
	@NotNull(message="Username is a required field!")
	@Size(min=1, max=32, message="Username must be between 1 and 32 characters!")
	private String username;
	
	/**
	 * Password of the user
	 */
	@NotNull(message="Password is a required field!")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters!")
	private String password;

	/**
	 * Id of the user associated with this credential
	 */
	private int userId;
	
	/**
	 * Id of the CredentialModel
	 */
	private int credentialsId;
	
	/**
	 * Default Constructor for CredentialsModel
	 */
	public CredentialsModel() {
		
	}
	
	/**
	 * non default constructor for CredentialsModel
	 * @param username String passed in
	 * @param password String passed in
	 * @param credentialsId Int passed in
	 */
	public CredentialsModel(String username, String password, int credentialsId) {
		this.username = username;
		this.password = password;
		this.credentialsId = credentialsId;
	}
	
	/**
	 * gets userId
	 * @return userId int
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * sets userId
	 * @param userId int passed in
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * gets username
	 * @return username string
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * gets password
	 * @return password string
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * sets username
	 * @param username string passed in
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * sets password
	 * @param password string passed in
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
     * @return the credentialsId 
     */
    public int getCredentialsId() {
        return credentialsId;
    }

    /**
     * @param credentialsId passed in
     * sets the credentials id for the user
     */
    public void setCredentialsId(int credentialsId) {
        this.credentialsId = credentialsId;
    }
}
