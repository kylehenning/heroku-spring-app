package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Creates the Index Controller
 * @author connorrolstad
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {
	/**
	 * Method for displaying the Welcome Page 
	 * @return index.html
	 */
	@GetMapping("/")
	public String display() {
		return "index";
	}
}
