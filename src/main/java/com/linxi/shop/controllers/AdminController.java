package com.linxi.shop.controllers;

import com.linxi.shop.models.Customer;
import com.linxi.shop.models.CustomerRepository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
  @Autowired
  private CustomerRepository customerRepository;
  
  @RequestMapping(value="/createcustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String customerCreate(@RequestBody Customer customerFromUI) {
    Customer customer = null;
    try {
      customer = customerFromUI;
      customerRepository.save(customer);
    }
    catch (Exception ex) {
      return "Error creating the customer: " + ex.toString();
    }
    String json = new Gson().toJson(customer );
    return json;
  }
  
  @RequestMapping(value="/deletecustomer/{customerid}", method = RequestMethod.DELETE)
  @ResponseBody
  public String delete(@PathVariable("customerid") String  customerid) {
    try {
    	
      long customeridlong = NumberUtils.toLong(customerid);
      Customer customer = new Customer(customeridlong);
      customerRepository.delete(customer);
    }
    catch (Exception ex) {
      return "Error deleting the customer: " + ex.toString();
    }
    return "Customer succesfully deleted!";
  }
  
  @RequestMapping("/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String customerId;
    try {
      Customer customer = customerRepository.findByEmail(email);
      customerId = String.valueOf(customer.getCustomerid());
    }
    catch (Exception ex) {
      return "Customer not found";
    }
    return "The customer id is: " + customerId;
  }
  
  @RequestMapping(value = "/listCustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String listCustomers() {
    List<Customer> listCustomers;
    try {
      listCustomers = (List<Customer>) customerRepository.findAll();
    }
    catch (Exception ex) {
      return null;
    }
    String json = new Gson().toJson(listCustomers );
    return json;
  }
  
  @RequestMapping(value="/updatecustomer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateCustomer(Customer customerFromUI) {
    Customer customer = null;
    
    //TODO: provide findbyid method in customerDao to check customer exist or not
    try {
      customer = customerFromUI;
      customerRepository.save(customer);
    }
    catch (Exception ex) {
      return "Error creating the customer: " + ex.toString();
    }
    String json = new Gson().toJson(customer );
    return json;
  }
  
  
}
