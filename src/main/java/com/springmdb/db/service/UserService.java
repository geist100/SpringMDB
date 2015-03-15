package com.springmdb.db.service;

import java.util.List;

import com.springmdb.db.entity.User;


public interface UserService {

	public List<User> allUsers();
	public void deleteUser(String id);
	public void updateUser(User user);
	public void addUser(User user);
}
