package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.data.mapper.ProductsRowMapper;
import com.gcu.model.ProductModel;
import com.gcu.util.DatabaseException;

/**
 * Creates the ProductsDataService
 * @author connorrolstad
 *
 */
@Service
public class ProductsDataService implements DataAccessInterface<ProductModel> {

	/**
	 * This contains how to talk to the mysql server
	 */
	@SuppressWarnings("unused")
	@Autowired
	private DataSource datasource;
	/**
	 * This is used to execute the sql statements on the datasource
	 */
	private JdbcTemplate jdbcTemplateObject;
	
	/**
	 * Non-Default Constructor for the PDS
	 * @param datasource passed in
	 */
	ProductsDataService(DataSource datasource) {
		this.datasource = datasource;
		this.jdbcTemplateObject = new JdbcTemplate(datasource);
	}

	/**
	 * gets all products from products table
	 * @return list of products
	 * @throws DatabaseException something went wrong with the database
	 */
	@Override
	public List<ProductModel> findAll() {

		String sql = "SELECT * FROM products";
		List<ProductModel> products = new ArrayList<ProductModel>();
		
		try {
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next()) {
				products.add(new ProductModel(
						srs.getInt(1),//products_id
						srs.getString(2),//name
						srs.getString(3),//genre
						srs.getString(4),//artist_name
						srs.getString(5)//image
						));
			}
		} catch (Exception e) {
			throw new DatabaseException(e, "Database Error");
		}
		
		return products;
	}

	/**
	 * finds product based on id passed in
	 * @param id passed in
	 * @return ProductModel found
	 * @throws DatabaseException something went wrong with the database
	 */
	@Override
	public ProductModel findById(int id) {
		
		String sql = "SELECT * FROM products WHERE products_id = ?";
		ProductModel product;
		
		try {
			product = jdbcTemplateObject.queryForObject(sql, new ProductsRowMapper(), id);
		}
		// catch when the queryForObject throws the empty result data access exception
		catch (EmptyResultDataAccessException e) {
			// return null so that the get product by id rest service will correctly show the Not found status when an incorrect id is searched
			return null;
		}
		catch (Exception e) {
			throw new DatabaseException(e, "Database Error");
		}
		return product;
	}

	/**
	 * creates a new product in the products table
	 * @param p passed in
	 * @return int rows changed (should be 1)
	 * @throws DatabaseException something went wrong with the database
	 */
	@Override
	public int create(ProductModel p) {
		
		String sql = "INSERT INTO products(name, genre, artist_name, image) VALUES (?, ?, ?, ?)";
		int rows = -1;
		try {
			rows = jdbcTemplateObject.update(sql,
					p.getName(),
					p.getGenre(),
					p.getArtistName(),
					p.getImage()
					);
		} catch (Exception e) {
			throw new DatabaseException(e, "Database Error");
		}
		
		return rows;
	}

	/**
	 * 
	 * update product in the products table based on the id of the product passed in
	 * @param p product passed in
	 * @return int rows updated from query
	 * @throws DatabaseException something went wrong with the database
	 */
	@Override
	public int update(ProductModel p) {
		String sql = "UPDATE products SET name = ?, genre = ?, artist_name = ?, image = ? WHERE products_id = ?";
		int rows = -1;
		try {
			rows = jdbcTemplateObject.update(sql,
					p.getName(),
					p.getGenre(),
					p.getArtistName(),
					p.getImage(),
					p.getProductId()
					);
		} catch (Exception e) {
			throw new DatabaseException(e, "Database Error");
		}
		
		return rows;
	}

	/**
	 * Connor
	 * delete product(M5)
	 * @param id product id passed in
	 * @return boolean updated or not
	 */
	@Override
	public boolean delete(int id) {
		
		String sql = "DELETE FROM products WHERE products_id = ?";
		int rows = -1;
		boolean ret = false;
		
		try {
			rows = jdbcTemplateObject.update(sql, id);
			if (rows != -1) {
				ret = true;
			}
		} catch (Exception e) {
			throw new DatabaseException(e, "Database Error");
		}
		return ret;
	}
}
