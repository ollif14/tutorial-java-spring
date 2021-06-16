package com.example.demo.redisTest.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.redisTest.model.User;
import com.example.demo.redisTest.repository.UserRepository;

@RestController
@RequestMapping("/redis")
public class UserRedisController {
		
	@Autowired
	private UserRepository repo;
	 

	public UserRedisController(UserRepository repo) {
		super();
		this.repo = repo;
	   }

	@GetMapping(value = "/user", consumes = {"application/json", "application/xml" })
	public Map GetAll(){
		return repo.findAll();
	}

	@GetMapping("/user/{id}")
	public User GetAll(@PathVariable("id") Long id){
	    return repo.findById(id);
	}

	@PostMapping("/user")
	public User add(@RequestBody User user){
	    repo.save(new User(user.getId(),user.getName(),user.getSalary()));
	    System.out.println("Add Success");
	    return repo.findById(user.getId());
	}

	@PutMapping("/user/{id}")
	public User update(@PathVariable(value = "id") Long id, @RequestBody User user){
		User u = repo.findById(id);
		u.setId(user.getId());
		u.setName(user.getName());
		u.setSalary(user.getSalary());
	    if(id == user.getId()) {
	    	repo.update(u);
	    }
	    else {
	    	repo.update(u);
	    	repo.delete(id);
	    }
	    System.out.println("Update Success");
	    return repo.findById(u.getId());
	}
	
	@DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Long id) {
        repo.delete(id);
        System.out.println("delete success");
        return "Delete Success";
    }
}
