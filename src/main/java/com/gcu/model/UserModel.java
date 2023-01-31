package com.gcu.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Creates the UserModel Class
 * @author kyleb
 *
 */
public class UserModel {
    /**
     * represents this user's firstname
     */
    @NotNull(message="First Name is a required field")
    @Size(min=1, max=32, message="First Name must be between 1 and 32 characters")
    private String firstName;
    
    /**
     * represents this user's last name
     */
    @NotNull(message="Last Name is a required field")
    @Size(min=1, max=32, message="Last Name must be between 1 and 32 characters")
    private String lastName;
    
    /**
     * represents this user's email
     */
    @NotNull(message="Email Address is a required field")
    @Size(min=1, max=50, message="Email Address must be between 1 and 50 characters")
    @Email(message="Email should be in valid format")
    private String emailAddress;
    /**
     * represents this user's phone number
     */
    @NotNull(message="Phone Number is a required field")
    @Pattern(regexp="^\\d{10}$", message="Phone Number must be 10 digits Example: (8002752273)")
    private String phoneNumber;

    /**
     * represents the unique identifier of this user
     */
    private int userId;
    
    /**
     * Represents the credentials of this user
     */
    @Valid
    private CredentialsModel credentials;
    
    /**
     * Non-default constructor for User Model
     * @param firstName string passed in
     * @param lastName string passed in
     * @param emailAddress string passed in
     * @param phoneNumber string passed in
     * @param credentials credentials model passed in
     * @param userId int passed in
     */
    public UserModel(String firstName, String lastName, String emailAddress, String phoneNumber, CredentialsModel credentials, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.credentials = credentials;
        this.userId = userId;
    }
    
    /**
     * Default constructor for User Model that HAS A CredentialsModel
     */
    public UserModel() {
        this.credentials = new CredentialsModel();
    }

    
    /**
     * @return the firstName of the user
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName passed in
     * sets the firstName of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return the lastName of the user
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName passed in
     * sets the lastName of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return the emailAddress of the user
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    /**
     * @param emailAddress passed in
     * sets the emailAddress of the user
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    /**
     * @return the phoneNumber of the user
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * @param phoneNumber passed in
     * sets the phoneNumber of the user
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * @return the credentials information about the user
     */
    public CredentialsModel getCredentials() {
        return credentials;
    }
    /**
     * @param credentials passed in
     * sets the credentials of the user
     */
    public void setCredentials(CredentialsModel credentials) {
        this.credentials = credentials;
    }
    /**
     * @return the userId of the user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId int passed in
     * sets the user id for the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
