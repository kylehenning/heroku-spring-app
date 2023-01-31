package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Creates the ProductModel Class
 * @author connorrolstad
 *
 */
public class ProductModel {
	
//  Set up variables for each product and proper validation
	
	/**
	 * Represents a song's name
	 */
	@NotNull(message="Product Name is a required field")
	@Size(min=1, max=32, message="Song Name must be between 1 and 32 characters")
	private String name;
	/**
	 * Represents a song's genre
	 */
	@NotNull(message="genre is a required field")
	@Size(min=1, max=32, message="Genre must be between 1 and 32 characters")
	private String genre;
	/**
	 * Represents a song's artist
	 */
	@NotNull(message="artist name is a required field")
	@Size(min=1, max=50, message="Artist Name must be between 1 and 50 characters")
	private String artistName;
	/**
	 * Represents the link to an image
	 */
	@NotNull(message="Song Image Address is a required field")
	@Size(min=1, max=1000000, message="Song Image Address must be between 1 and 1000000 characters")
	private String image;
	/**
	 * Represents a unique product identifier
	 */
	private int productId;
	
	
	/**
	 * Non-default constructor for ProductModel
	 * @param name String passed in
	 * @param genre String passed in
	 * @param artistName String passed in
	 * @param productId int for the id of the product
	 * @param image String for image address
	 */
	public ProductModel(int productId, String name, String genre, String artistName, String image) {
		this.name = name;
		this.genre = genre;
		this.artistName = artistName;
		this.productId = productId;
		this.image = image;
	}

	/**
	 * Default constructor for ProductModel
	 */
	public ProductModel() {
		
	}

	/**
	 * getter for product name
	 * @return name string of product
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for product name
	 * @param name to set product to
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter for product genre
	 * @return the genre of the product
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * setter for product genre
	 * @param genre to set product to
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * getter for artist of the product
	 * @return the artistName for the product
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * setter for artist of product
	 * @param artistName to set product to
	 */
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	/**
	 * getter for product id
	 * @return the productId int
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * setter for product id
	 * @param productId to set product to
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * getter for the image address of the product
	 * @return the image address string
	*/
	public String getImage() {
		return image;
	}

	/**
	 * setter for product image address
	 * @param image to set product to
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
