package com.springmdb.db.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmdb.db.entity.User;
import com.springmdb.db.repository.UserRepository;

@Service(value= "userService")
public class UserServiceImpl implements UserService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1794928175540384017L;
	@Autowired
	private UserRepository userRepository;
	@Override
	public List<User> allUsers() {
		return userRepository.allUsers();
	}
	@Override
	public void deleteUser(String id) {
		userRepository.deleteUser(id);
	}
	@Override
	public void updateUser(User user) {
		userRepository.updateUser(user);
	}
	@Override
	public void addUser(User user) {
		userRepository.addUser(user);
	}
}
