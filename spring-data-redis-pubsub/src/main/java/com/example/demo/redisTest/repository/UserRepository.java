package com.example.demo.redisTest.repository;

import java.util.Map;

import com.example.demo.redisTest.model.User;

public interface UserRepository {
	//create
	void save(User user);

	//read
    Map findAll();
    User findById(Long id);
    
    //update
    void update(User user);
    
    //delete
    void delete(Long id);
}
