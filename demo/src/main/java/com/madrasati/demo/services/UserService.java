package com.madrasati.demo.services;

import java.util.List;
import java.util.Optional;

import com.madrasati.demo.model.User;
import com.madrasati.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		//Optional<User> employ=userRepository.findByEmailUser(user.getEmail());
		//user.setPassword(employ.get().getPassword());
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);
		
	}

	

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
		
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

}
