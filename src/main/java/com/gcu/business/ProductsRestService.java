package com.gcu.business;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.ProductModel;



/**
 * Our Products Rest Service class which holds the logic needed for the REST API
 * @author kyleb
 *
 */
@RestController
@RequestMapping("/service")
public class ProductsRestService {
    
//  Injects the Products business service interface
    @Autowired
    ProductsBusinessServiceInterface service;
    
    /**
     * Method Returns a desired product by a specified id in the path
     * @param id integer passed in from the path variable
     * @return ResponseEntity displaying a successful JSON result of a product or an HTTP status error
     */
    @GetMapping(path="/getproduct/{productId}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getProductById(@PathVariable(value="productId") int id)
    {
        try 
        {
            // After getting the product id as a path variable it is passed in to the getProductById method from the products buisness service
            ProductModel product = service.getProductById(id);
            // If the product is null then the product matching the specified id was not found in the database
            if(product == null)
            {
                // return a response entity displaying the not found status to the user
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                // This returns a single product in JSON format specified by the product id
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        // Catch any exception that could be thrown by the Database
        catch(Exception e)
        {
            // return a response entity displaying the internal server error status
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Method Returns all products in database in JSON
     * @return the list of products in JSON or an HTTP status error
     */
    @GetMapping(path="/getallproducts", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllProducts()
    {
        try 
        {
            // Calls the products business service method getProducts to return all the products in our application
            List<ProductModel> products = service.getProducts();
            
            // Returns with a status of OK displaying all products from the List
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        // Catch any exception that could be thrown by the Database
        catch(Exception e)
        {
            // return a response entity displaying the internal server error status
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
