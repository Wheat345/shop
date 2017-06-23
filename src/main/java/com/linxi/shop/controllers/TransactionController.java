package com.linxi.shop.controllers;

import com.linxi.shop.models.Transaction;
import com.linxi.shop.models.TransactionRepository;
import com.linxi.shop.models.User;
import com.linxi.shop.models.UserRepository;

import java.util.Date;
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
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value="/rest")
public class TransactionController {
	
  @Autowired
  private TransactionRepository transactionRepository;
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
  
  @RequestMapping(value="/createtransaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String transactionCreate(@RequestBody Transaction transactionFromUI) {
	  Transaction transaction = null;
    try {
    	transaction = transactionFromUI;
      user = this.getCurrentCredential();
      transaction.setUserid(user.getUserid());
      
      transactionRepository.save(transaction);
    }
    catch (Exception ex) {
      return "Error creating the transaction: " + ex.toString();
    }
    String json = new Gson().toJson(transaction);
    return json;
  }
  
  @RequestMapping(value="/deletetransaction/{transactionid}", method = RequestMethod.DELETE)
  @ResponseBody
  public String delete(@PathVariable("transactionid") String  transactionid) {
    try {
    	
      long transactionidlong = NumberUtils.toLong(transactionid);
      Transaction transaction = new Transaction(transactionidlong);
      transactionRepository.delete(transaction);
    }
    catch (Exception ex) {
      return "Error deleting the transaction: " + ex.toString();
    }
    return "Transaction succesfully deleted!";
  }
    
  @RequestMapping(value = "/listtransactions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String listpTransactions() {
    List<Transaction> listTransactions;
    try {
      user = this.getCurrentCredential();
      listTransactions = (List<Transaction>) transactionRepository.findAllbyUserId(user.getUserid());
    }
    catch (Exception ex) {
      return null;
    }
    String json = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create().toJson(listTransactions);//format date to suit ui 
    return json;
  }
  
  @RequestMapping(value="/updatetransaction", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String updateTransaction(@RequestBody Transaction transactionFromUI) {
	  Transaction transaction = null;
    
    try {
    	transaction = transactionFromUI;
    	transactionRepository.save(transaction);
    }
    catch (Exception ex) {
      return "Error creating the transaction: " + ex.toString();
    }
    String json = new Gson().toJson(transaction);
    return json;
  }
  
  
}
