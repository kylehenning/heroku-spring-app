package com.gcu.util;

/**
 * Creates the DatabaseException
 * @author connorrolstad
 *
 */
public class DatabaseException extends RuntimeException {
	/**
	 * This acts like the index for custom exceptions
	 */
    private static final long serialVersionUID = 1;

    /**
     * Default constructor for the database exception
     */
    public DatabaseException()
    {
        super();
    }

    /**
     * Non-Default constructor for the database exception
     * calls the RuntimeException constructor
     * @param err the actual error that was thrown
     * @param errorMessage what will be printed to the console
     */
    public DatabaseException(Throwable err, String errorMessage)
    {
        super(errorMessage, err);
    }
}
