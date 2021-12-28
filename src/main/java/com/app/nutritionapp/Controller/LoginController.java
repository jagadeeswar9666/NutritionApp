package com.app.nutritionapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.nutritionapp.Model.User;
import com.app.nutritionapp.ServiceIMPL.LoginServiceImpl;

@RestController
public class LoginController {
	
	    @Autowired
	    LoginServiceImpl service;
	 
	    // user login
	    @CrossOrigin
	    @PostMapping(path = "/login")
	    public User login(@RequestBody User user) {
	 
	        return service.login(user);
	    }

}
