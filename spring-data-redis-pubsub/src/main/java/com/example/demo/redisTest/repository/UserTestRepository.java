package com.example.demo.redisTest.repository;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.redisTest.model.User;

@Repository
public class UserTestRepository implements UserRepository{
	
	private RedisTemplate redisTemplate;
    private HashOperations hashOperations; 
    
	public UserTestRepository(RedisTemplate redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		hashOperations.put("USER",user.getId(),user);
	}

	@Override
	public Map findAll() {
		// TODO Auto-generated method stub
		return hashOperations.entries("USER");
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return (User)hashOperations.get("USER",id);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		 save(user);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		 hashOperations.delete("USER",id);
	}

}
