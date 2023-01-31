package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.business.UserBusinessServiceInterface;
import com.gcu.model.UserModel;
import com.gcu.util.DatabaseException;

/**
 * Creates the Registration Controller Class
 * @author kyleb
 *
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {
	/**
	 * Injects the user business service interface
	 */
	@Autowired
	private UserBusinessServiceInterface service;

    /**
     * Method for displaying the Registration Form
     * @param model is passed in so that attributes can be added to it
     * @return the register.html page
     */
    @GetMapping("/")
    public String display(Model model) {
        model.addAttribute("title", "Registration Form");
        model.addAttribute("userModel", new UserModel());
        return "register";
    }
    
    /**
     * Method that will handle processing registration logic
     * @param userModel UserModel passed in
     * @param bindingResult binding result passed in
     * @param model model passed in
     * @param redirectAttrs passed in to send attributes on redirect
     * @throws DatabaseException something went wrong with the database
     * @return back to register.html if errors or on to the products application if registration success
     */
    @PostMapping("/doRegister")
    public String doRegister(@Valid UserModel userModel, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) throws DatabaseException {
        
        // Checks for errors in registration form     
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            System.out.println(String.format("Failed Registration with %s, %s, %s, %s, %s, %s", userModel.getFirstName(), userModel.getLastName(),
                    userModel.getEmailAddress(), userModel.getPhoneNumber(), userModel.getCredentials().getUsername(),
                    userModel.getCredentials().getPassword()));
            model.addAttribute("title", "Failed Register");
            //    Returns the register.html file if registration failure        
            return "register";
        } else {
            // Prints user model data to console           
            System.out.println(String.format("Good Register call with %s, %s, %s, %s, %s, %s", userModel.getFirstName(), userModel.getLastName(),
                    userModel.getEmailAddress(), userModel.getPhoneNumber(), userModel.getCredentials().getUsername(),
                    userModel.getCredentials().getPassword()));
        }
        
//      use the user service to create the user and add the user to the user list
        service.createUser(userModel);      

        // Add attributes for successful registration  
        redirectAttrs.addAttribute("successMessage", "Congrats on registering " + userModel.getCredentials().getUsername() + "!");
//      redirect to products page
        return "redirect:/products/";
    }
}
