package com.gcu.business;

import java.util.List;

import com.gcu.model.ProductModel;

/**
 * Product business service interface class
 * @author kyleb
 *
 */
public interface ProductsBusinessServiceInterface {
    /**
     * gets the products from the products list
     * @return products list
     */
	public List<ProductModel> getProducts();
    /**
     * Adds a product to the product list
     * @param productModel passed in
     * @return the productModel that was added to products list
     */
	public ProductModel addProduct(ProductModel productModel);
	/**
	 * Deletes a product from the database
	 * @param id passed in
	 * @return whether the product was deleted
	 */
	public boolean deleteProduct(int id);
   /**
     * updates product
     * @param productModel passed in from controller
     * @return ProductModel that has been updated
     */
     public ProductModel updateProduct(ProductModel productModel);
     /**
      * Finds a Product in the database based on id passed in
      * @param productId passed in from controller
      * @return ProductModel that has been found in db
      */
     public ProductModel getProductById(int productId);
   /**
     * Product service init method
     */
	public void init();
    /**
     * Product service destroy method
     */
	public void destroy();
}
