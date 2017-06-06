package com.linxi.shop.controllers;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableWebSecurity
public class MainController {

  @RequestMapping("/")
  @ResponseBody
  @CrossOrigin(origins = "*", maxAge = 3600)
  public String index() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName(); //get logged in username
    //return "Welcome to shop";
    String welcome = "Welcome to shop," + name;
    String json = new Gson().toJson(welcome);
    //return "home";
    return json;
  }

}
