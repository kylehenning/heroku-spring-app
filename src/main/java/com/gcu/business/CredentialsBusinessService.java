package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.CredentialsDataService;

/**
 * Credentials Business Service class to handle services for credentials
 * @author kyleb
 *
 */
public class CredentialsBusinessService implements CredentialsBusinessServiceInterface {

	/**
	 * Deals with accessing credentials from the database
	 */
    @Autowired
    CredentialsDataService credentialsDataService;

    /**
     * init method
     */
    @Override
    public void init() {
        System.out.println("Credentials Service init");

    }
    
    /**
     * destroy method
     */
    @Override
    public void destroy() {
        System.out.println("Credentials Service destroy");

    }

}
