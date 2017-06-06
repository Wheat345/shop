package com.linxi.shop.controllers;

import com.linxi.shop.models.Customer;
import com.linxi.shop.models.CustomerDao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

/**
 * The Class CustomerController.
 */
@Controller
@RequestMapping(value="/rest")
public class CustomerController {
	
  /** The customer dao. */
  @Autowired
  private CustomerDao customerDao;

  /**
   * Customer create.
   *
   * @param customerFromUI the customer from UI
   * @return the string
   */
  @RequestMapping(value="/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String customerCreate(Customer customerFromUI) {
    Customer customer = null;
    try {
      customer = customerFromUI;
      customerDao.save(customer);
    }
    catch (Exception ex) {
      return "Error creating the customer: " + ex.toString();
    }
    String json = new Gson().toJson(customer );
    return json;
  }
  
  /**
   * Delete.
   *
   * @param id the id
   * @return the string
   */
  @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public String delete(@PathVariable("id") String  id) {
    try {
      Customer customer = new Customer(Long.parseLong(id));
      customerDao.delete(customer);
    }
    catch (Exception ex) {
      return "Error deleting the customer: " + ex.toString();
    }
    return "Customer succesfully deleted!";
  }
  
  /**
   * Gets the by email.
   *
   * @param email the email
   * @return the by email
   */
  @RequestMapping("/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String customerId;
    try {
      Customer customer = customerDao.findByEmail(email);
      customerId = String.valueOf(customer.getId());
    }
    catch (Exception ex) {
      return "Customer not found";
    }
    return "The customer id is: " + customerId;
  }
  
  /**
   * List customers.
   *
   * @return the string
   */
  @RequestMapping(value = "/listCustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String listCustomers() {
    List<Customer> listCustomers;
    try {
      listCustomers = (List<Customer>) customerDao.findAll();
    }
    catch (Exception ex) {
      return null;
    }
    String json = new Gson().toJson(listCustomers );
    return json;
  }
  
  /**
   * Update customer.
   *
   * @param customerFromUI the customer from UI
   * @return the string
   */
  @RequestMapping(value="/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateCustomer(Customer customerFromUI) {
    Customer customer = null;
    
    //TODO: provide findbyid method in customerDao to check customer exist or not
    try {
      customer = customerFromUI;
      customerDao.save(customer);
    }
    catch (Exception ex) {
      return "Error creating the customer: " + ex.toString();
    }
    String json = new Gson().toJson(customer );
    return json;
  }
  
  @RequestMapping(value="/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null){    
          new SecurityContextLogoutHandler().logout(request, response, auth);
      }
      return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
  }
  
}
