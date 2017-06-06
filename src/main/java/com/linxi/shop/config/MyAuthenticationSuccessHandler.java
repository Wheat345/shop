package com.linxi.shop.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    //@Autowired
    //private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //if (auth == null && auth.isAuthenticated()) {
        if (auth.isAuthenticated()) {
            String username = auth.getPrincipal().toString();
            System.out.println("username is " + username);
            //UserData userData = userService.getUserData(username);
            // And then here more checks, handlings etc.
        }

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin");
        //response.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        super.onAuthenticationSuccess(request, response, authentication);
    }

}