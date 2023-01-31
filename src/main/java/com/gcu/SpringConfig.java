package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.business.CredentialsBusinessService;
import com.gcu.business.CredentialsBusinessServiceInterface;
import com.gcu.business.ProductsBusinessService;
import com.gcu.business.ProductsBusinessServiceInterface;
import com.gcu.business.UserBusinessService;
import com.gcu.business.UserBusinessServiceInterface;

/**
 * Spring Config class
 * @author kyleb
 *
 */
@Configuration
public class SpringConfig {
    
    /**
     * Configures the Spring Bean for products business service
     * @return the product business service
     */
	@Bean(name="productsBusinessService", initMethod="init", destroyMethod="destroy")
	public ProductsBusinessServiceInterface getProductsBusiness() {
		return new ProductsBusinessService();
	}
	
	/**
	 * configures the bean for the credentials business service
	 * @return the credentials business service
	 */
    @Bean(name="credentialsBusinessService", initMethod="init", destroyMethod="destroy")
    public CredentialsBusinessServiceInterface getCredentialsBusinessService() {
        return new CredentialsBusinessService();
    }

    /**
     * configures the bean for the user business service
     * @return the user business service
     */
    @Bean(name="userBusinessService", initMethod="init", destroyMethod="destroy")
    public UserBusinessServiceInterface getUserBusinessService() {
        return new UserBusinessService();
    }
}
