package com.gcu.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.model.ProductModel;

/**
 * Creates the ProductsRowMapper
 * @author connorrolstad
 *
 */
public class ProductsRowMapper implements RowMapper<ProductModel> {

	@Override
	public ProductModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductModel product = new ProductModel();
//		maps the returned data from query to a new product model
		product.setName(rs.getString("name"));
		product.setGenre(rs.getString("genre"));
		product.setArtistName(rs.getString("artist_name"));
		product.setImage(rs.getString("image"));
		product.setProductId(rs.getInt("products_id"));
		
		return product;
	}
}
