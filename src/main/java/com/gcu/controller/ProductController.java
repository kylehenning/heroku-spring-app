package com.gcu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// import com.gcu.business.CredentialsBusinessServiceInterface;
import com.gcu.business.ProductsBusinessServiceInterface;
import com.gcu.model.ProductModel;
import com.gcu.util.DatabaseException;

/**
 * Controller to handle product logic
 * @author kyleb
 *
 */
@Controller
@RequestMapping("/products")
public class ProductController {

	/**
	 * injects the product business service interface
	 */
	@Autowired
	private ProductsBusinessServiceInterface service;
	
	/**
	 * displays the products page
	 * @param model passed in
	 * @param flashAttribute passed in from the redirect
	 * @throws DatabaseException something went wrong with the database
	 * @return product.html
	 */
	@GetMapping("/")
	public String display(Model model, @ModelAttribute("successMessage") String flashAttribute) throws DatabaseException {
		//Get hardcoded list of products
		List<ProductModel> products = service.getProducts();
		
		//set model attributes
		model.addAttribute("title", "Products Page");
		model.addAttribute("products", products);
		model.addAttribute("redirectionAttr", flashAttribute);
		return "products";
	}
	
	/**
	 * Gets the add product page
	 * @param model passed in
	 * @return the add product page
	 */
	@PostMapping("/addProduct")
	public String addProduct(Model model)
	{
//	    setup model attributes for add product
		model.addAttribute("title", "Add Product Page");
		model.addAttribute("productModel", new ProductModel());
		return "addProduct";
	}
	
	/**
	 * Gets the update product page and populates it with product information
	 * @param productId passed in through RequestParam to determine which product is being updated
	 * @param model passed in
	 * @throws DatabaseException something went wrong with the database
	 * @return the update product page
	 */
	@PostMapping("/updateProduct")
	public String updateProduct(@RequestParam(value = "update") int productId, Model model) throws DatabaseException
	{
		model.addAttribute("title", "Update Product Page");
		// finds the product that the user would like to update and passes it in as an attribute
		ProductModel product = service.getProductById(productId);
		model.addAttribute("productModel", product);
		return "updateProduct";
	}

	/**
	 * Gets the product that the user requests to view and sends them to view product page
	 * @param productId passed in through RequestParam to determine which product is being viewed
	 * @param model passed in
	 * @throws DatabaseException something went wrong with the database
	 * @return the view product page
	 */
	@PostMapping("/viewProduct")
	public String viewProduct(@RequestParam(value = "view") int productId, Model model) throws DatabaseException
	{
		model.addAttribute("title", "View Product Page");
		// Finds the product that the user wants to view and passes it in as an attribute to the view product page
		ProductModel product = service.getProductById(productId);
		model.addAttribute("productModel", product);
		return "viewProduct";
	}

	/**
	 * do add product form submission
	 * @param productModel passed in
	 * @param bindingResult passed in for error checking
	 * @param model passed in
	 * @throws DatabaseException something went wrong with the database
	 * @return addProduct.html if errors or the products.html if successful in adding product
	 */
	@PostMapping("/doAddProduct")
    public String doAddProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model) throws DatabaseException
	{
//      Checks for errors in form data for add product
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult);
			System.out.println(String.format("Bad Add Product call with %s, %s, %s", productModel.getName(), productModel.getArtistName(),
			productModel.getGenre()));
            model.addAttribute("title", "Failed Add Product");
                  
            return "addProduct";
        } else {
            // Prints user model data to console           
            System.out.println(String.format("Good Add Product call with %s, %s, %s", productModel.getName(), productModel.getArtistName(),
			productModel.getGenre()));
        }
//      use product service to add the product to the products list
		service.addProduct(productModel);
//		get the products list
		List<ProductModel> products = service.getProducts();

		// Add attributes for successful add product  
		model.addAttribute("title", "Products Page");
		model.addAttribute("products", products);
		return display(model, null);
    }
	
	/**
	 * Updates the product on form submission
     * @param productModel passed in
     * @param bindingResult passed in for error checking
     * @param model passed in
	 * @throws DatabaseException something went wrong with the database
	 * @return updateProduct page if there are field errors. Products page if Successful update. ExceptionHandler page if database error.
	 */
	@PostMapping("/doUpdateProduct")
    public String doUpdateProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model) throws DatabaseException
	{
//      Checks for errors in form data for update product
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult);
			System.out.println(String.format("Bad Update Product call with %s, %s, %s", productModel.getName(), productModel.getArtistName(),
			productModel.getGenre()));
            model.addAttribute("title", "Failed Update Product");
                  
            return "updateProduct";
        } else {
            // Prints user model data to console           
            System.out.println(String.format("Good Update Product call with %s, %s, %s", productModel.getName(), productModel.getArtistName(),
			productModel.getGenre()));
        }
//      use product service to update the specified product in the products list
		service.updateProduct(productModel);
		
//		get the products list
		List<ProductModel> products = service.getProducts();

		// Add attributes for successful update product  
		model.addAttribute("title", "Products Page");
		model.addAttribute("products", products);
		return "products";
    }
	/** 
	* Deletes a product from the database
	 * @param productId passed in
	 * @param model passed in
	 * @throws DatabaseException something went wrong with the database
	 * @return products.html
	 */
	@PostMapping("/deleteProduct")
	public String deleteProduct(@RequestParam(value = "delete") int productId, Model model) throws DatabaseException {
		//Catch from the popup confirm in products.html
		if(productId != -1) {
			System.out.println("Deleting " + productId);
			// deletes the product by the product id passed in from RequestParam
			service.deleteProduct(productId);
		}
		
		//		get the products list
		List<ProductModel> products = service.getProducts();
		
		// Add attributes for successful add product  
		model.addAttribute("title", "Products Page");
		model.addAttribute("products", products);
		return "products";
	}
}
