package com.madrasati.demo.services;

import java.util.List;

import com.madrasati.demo.model.User;

public interface IUserService {
	
	
	User saveUser(User user);
	User updateUser(User user);
	void deleteUser(User user);
	void deleteUserById(Long id);
	User getUser(Long id);
	List<User> getAllUser();

}
