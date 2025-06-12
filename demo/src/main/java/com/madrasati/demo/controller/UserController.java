package com.madrasati.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.madrasati.demo.model.User;
import com.madrasati.demo.services.IUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> getAllUser()
	{
		return userService.getAllUser();
	}
	
	@RequestMapping(value="/user/{id}",method = RequestMethod.GET)
	public User getUserById(@PathVariable("id") Long id)
	{
		return userService.getUser(id)
;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User createUser(@RequestBody User user)
	{
		
			return userService.saveUser(user);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) 
	{
		return userService.updateUser(user);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") Long id)
	{
		userService.deleteUserById(id);
;
	}

}
