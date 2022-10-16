package com.example.demo.app.user.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.app.user.entity.User;
import com.example.demo.app.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Transactional
	public User registerUser(User user) {
		
			return userRepository.save(user);
	
	}
	public void deleteUser(String userId){
		User user= userRepository.findCurrentUserId(userId);
		userRepository.delete(user);
	}
}
