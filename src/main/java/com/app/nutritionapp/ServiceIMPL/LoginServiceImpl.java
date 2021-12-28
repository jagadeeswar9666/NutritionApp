package com.app.nutritionapp.ServiceIMPL;

import java.util.Optional;

//import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.nutritionapp.Exception.UserNotExistsException;
import com.app.nutritionapp.Model.NutritionPlan;
import com.app.nutritionapp.Model.User;
import com.app.nutritionapp.Service.ILoginService;
 
//import com.cp.entities.User;
import com.app.nutritionapp.Repository.UserRepository;
 
@Service
public class LoginServiceImpl implements ILoginService {
 
    @Autowired
    UserRepository repository;
 
    @Override
    public User login(User user) {
	
        User userResult = null;
        Optional<User> opt = repository.findById(user.getUserId());                        
    	User u = repository.findByLoginName(user);                         
        if (opt.isPresent()) {
            userResult = opt.get();
            if (userResult.getPassword().equals(user.getPassword()) && userResult.equals(user)   )            
            		{
                return userResult;
            } else {
                return null;
            }
        }

        return userResult;
    }
    
}