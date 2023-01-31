package com.gcu.data;

import java.util.List;

/**
 * Interface Class for the Data Services
 * uses a generic type
 * @author connorrolstad
 *
 */
public interface DataAccessInterface <T> {
	/**
	 * returns all the T in the database
	 * @return List of T
	 */
    public List<T> findAll();
    /**
     * Returns a T with the id of id
     * @param id passed in
     * @return T with the id of id
     */
    public T findById(int id);
    /**
     * Adds a T to the database
     * @param t passed in
     * @return the id of the T created
     */
    public int create(T t);
    /**
     * updates a T in the database
     * @param t passed in
     * @return int updated or not
     */
    public int update(T t);
    /**
     * deletes a T in the database
     * @param id passed in
     * @return boolean deleted or not
     */
    public boolean delete(int id);
}
