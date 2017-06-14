package com.linxi.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.linxi.shop.models.Customer;
import com.linxi.shop.models.User;
import com.linxi.shop.models.UserRepository;
import com.linxi.shop.models.UserRole;
import com.linxi.shop.models.UserRolesRepository;

@Controller
public class RegisterController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRolesRepository userRolesRepository;
	
	private static final String USER_ROLE_USER = "ROLE_USER";
	//private static final String USER_ROLE_ADMIN = "ROLE_ADMIN";
	
	
	
	@RequestMapping(value="/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String userCreate(@RequestBody User userFromUI) {
		User user = new User();
	  try {
			
		//check user existing
		User existUser = userRepository.findByEmail(userFromUI.getEmail());
		if (existUser != null) {
			//this.setStatus( HttpStatus.BAD_REQUEST  );
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			//@ResponseStatus(HttpStatus.CONFLICT)
			String json = new Gson().toJson("User already exist, please register by different email address.");
			return json;
		}
		user.setUserName(userFromUI.getUserName());
		user.setEmail(userFromUI.getEmail());
		//encrypt user password using spring security default method
		user.setPassword(passwordencoder().encode(userFromUI.getPassword()));
		user.setEnabled(userFromUI.getEnabled());
	    
	    userRepository.save(user);
	    
	    //set up user role
	    UserRole userRole = new UserRole();
	    userRole.setUserid(user.getUserid());
	    userRole.setRole(USER_ROLE_USER);
	    userRolesRepository.save(userRole);
	  }
	  catch (Exception ex) {
	    return "Error creating the user: " + ex.toString();
	  }
	  String json = new Gson().toJson(user);
	  return json;
	}
	

    
    @Bean(name="passwordEncoder")
       public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
       }

}
