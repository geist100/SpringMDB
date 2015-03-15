package com.springmdb.db.repository;

import java.util.List;

import com.springmdb.db.entity.User;

public interface UserRepository {

	public List<User> allUsers();
	public void deleteUser(String id);
	public void updateUser(User user);
	public void addUser(User user);
}
