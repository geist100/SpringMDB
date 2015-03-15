package com.springmdb.db.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.springmdb.db.entity.User;

@Repository(value="userRepository")
public class UserRepositoryImpl implements UserRepository,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2507006928892506198L;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<User> allUsers() {
		return mongoTemplate.findAll(User.class);
	}

	@Override
	public void deleteUser(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, User.class);
	}

	@Override
	public void updateUser(User user) {
		mongoTemplate.save(user);
	}

	@Override
	public void addUser(User user) {
		mongoTemplate.insert(user);
	}

}
