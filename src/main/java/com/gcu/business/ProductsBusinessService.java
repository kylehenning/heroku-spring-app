package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.ProductModel;

/**
 * Create class for product business service
 * @author kyleb
 *
 */
public class ProductsBusinessService implements ProductsBusinessServiceInterface {
	/**
	 * Deals with connecting to the database
	 */
    @Autowired
    DataAccessInterface<ProductModel> productService;
    
	
	/**
	 * Adds a product to the product list
	 * @param productModel passed in
	 * @return the productModel that was added to products list
	 */
	@Override
	public ProductModel addProduct(ProductModel productModel)
	{	
		//The productId gets added automatically in the database because the field is auto incrementing
		productService.create(productModel);
		return productModel;
	}
	
	/**
	 * gets the products from the dataService
	 * @return products list
	 */
	@Override
	public List<ProductModel> getProducts() {
		List<ProductModel> products = productService.findAll();
		return products;
	}
	
	/**
	 * deletes a product from the dataService
	 * @return whether it was successfull
	 */
	@Override
	public boolean deleteProduct(int id) {
		return productService.delete(id);
	}
	
	/**
	 * Product service init method
	 */
	@Override
	public void init() {
		System.out.println("Product Service init");
		
	}
	
    /**
     * Product service destroy method
     */
	@Override
	public void destroy() {
		System.out.println("Product Service destroy");
		
	}
	
	/**
	 * updates product
	 * @param productModel passed in from controller
	 * @return ProductModel that has been updated
	 */
	@Override
	public ProductModel updateProduct(ProductModel productModel) {
		productService.update(productModel);
		return productModel;
	}
	
	/**
	 * Finds a Product in the database based on id passed in
	 * @param productId passed in from controller
	 * @return ProductModel that has been found in db
	 */
	@Override
	public ProductModel getProductById(int productId) {
		return productService.findById(productId);
	}

}
