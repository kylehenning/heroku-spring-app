package com.gcu.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class designed to globally handle exceptions
 * @author kyleb
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
//  Initiate Logger variable
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * handles the database exception that is thrown
     * @param ex the exception that is thrown
     * @param model model passed in
     * @return the exceptionHandler html page
     */
    @ExceptionHandler(DatabaseException.class)
    public String handleDatabaseException(Exception ex, Model model) {
        
//      prints out a log of the stack trace and message attached to the exception
        LOGGER.error(ex.getMessage(), ex);
        
//      Add attributes on model to be viewed on exceptionHandler page
        model.addAttribute("title", "Error Page");
        model.addAttribute("exceptionMessage", ex.getMessage());

        return "exceptionHandler";
    }
}
