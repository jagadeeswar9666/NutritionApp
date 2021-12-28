package com.app.nutritionapp.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;

@Scope(scopeName = "prototype")
@Entity
public class Login {
	
	    @Id
	    private int id;
	    private String userName;
	    private String password;
	    private String role;
	 
	    public int getId() {
	        return id;
	    }
	 
	    public void setId(int id) {
	        this.id = id;
	    }
	 
	    public String getUserName() {
	        return userName;
	    }
	 
	    public void setUserName(String userName) {
	        this.userName = userName;
	    }
	 
	    public String getPassword() {
	        return password;
	    }
	 
	    public void setPassword(String password) {
	        this.password = password;
	    }
	 
	    public String getRole() {
	        return role;
	    }
	 
	    public void setRole(String role) {
	        this.role = role;
	    }
	 
}

