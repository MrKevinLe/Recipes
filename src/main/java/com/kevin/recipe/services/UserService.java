package com.kevin.recipe.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.kevin.recipe.models.LoginUser;
import com.kevin.recipe.models.User;
import com.kevin.recipe.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
//	FIND ALL
	public List<User>allUser(){
		return userRepo.findAll();
	}
//	CREATE
	public User createUser(User N) {
		return userRepo.save(N);
	}
//	FIND ONE BY ID
	public User findUser(Long id) {
		Optional<User> option = userRepo.findById(id);
		if(option.isPresent()) {
			return option.get();
		}else {
			return null;
		}
	}
//	DELETE USER BY ID
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	
//	REGSISTER USER
	 public User register(User user, BindingResult result) {
	        // TO-DO: Additional validations!
	    	Optional<User> potientail = userRepo.findByEmail(user.getEmail());
	    	if(potientail.isPresent()) {
	    		result.rejectValue("email", "error", "This email is taken");
	    	}
	    	if(!user.getPassword().equals(user.getConfirm())) {
	    		result.rejectValue("confirm", "error", "Password must Match");
	    	}
	    	if(result.hasErrors()) {
	    		return null;
	    		
	    	}else {
	    		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	    		user.setPassword(hashed);
	    		User newlyCreated = userRepo.save(user);
	    		return newlyCreated;
	    	}
	    }
	 
//	 LOGIN
	   public User login(LoginUser newLoginObject, BindingResult result) {
	        // TO-DO: Additional validations!
	    	Optional<User> potential = userRepo.findByEmail(newLoginObject.getEmail());
	    	if(!potential.isPresent()) {
	    		result.rejectValue("email","error", "email doest not exist");
	    	}else {
	    		User user = potential.get();
	    		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
	    			result.rejectValue("password", "errors", "invaild password");
	    		}
	    		if(result.hasErrors()) {
	    			return null;
	    		}else {
	    			return user;
	    		}
	    	}
	    		
	    	
	        return null;
	    }


}
