package com.linxi.shop.controllers;

import com.linxi.shop.models.Product;
import com.linxi.shop.models.ProductRepository;
import com.linxi.shop.models.User;
import com.linxi.shop.models.UserRepository;

import java.util.List;


import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value="/rest")
public class ProductController {
	
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private UserRepository userRepository;
  
  private User user;
  

private User getCurrentCredential() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName(); //get logged in username
      
      try {

          User user = userRepository.findByUserName(name);
          return user;
    	  
      }catch (Exception e) {
    	  
    	  System.out.println("can not find current credential");
    	  return null;
      }
  }
  
  @RequestMapping(value="/createproduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String productCreate(@RequestBody Product productFromUI) {
	  Product product = null;
    try {
    	product = productFromUI;
      user = this.getCurrentCredential();
      product.setUserid(user.getUserid());
      
      productRepository.save(product);
    }
    catch (Exception ex) {
      return "Error creating the product: " + ex.toString();
    }
    String json = new Gson().toJson(product );
    return json;
  }
  
  @RequestMapping(value="/deleteproduct/{productid}", method = RequestMethod.DELETE)
  @ResponseBody
  public String delete(@PathVariable("productid") String  productid) {
    try {
    	
      long productidlong = NumberUtils.toLong(productid);
      Product product = new Product(productidlong);
      productRepository.delete(product);
    }
    catch (Exception ex) {
      return "Error deleting the product: " + ex.toString();
    }
    return "Product succesfully deleted!";
  }
    
  @RequestMapping(value = "/listProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String listpProducts() {
    List<Product> listProducts;
    try {
      user = this.getCurrentCredential();
      listProducts = (List<Product>) productRepository.findAllbyUserId(user.getUserid());
    }
    catch (Exception ex) {
      return null;
    }
    String json = new Gson().toJson(listProducts );
    return json;
  }
  
  @RequestMapping(value="/updateproduct", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateProduct(@RequestBody Product productFromUI) {
	  Product product = null;
    
    try {
    	product = productFromUI;
    	productRepository.save(product);
    }
    catch (Exception ex) {
      return "Error creating the product: " + ex.toString();
    }
    String json = new Gson().toJson(product);
    return json;
  }
  
  
}
