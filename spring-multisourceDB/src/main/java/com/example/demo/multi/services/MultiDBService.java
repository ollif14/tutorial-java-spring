package com.example.demo.multi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.multi.mysql.model.Contact;
import com.example.demo.multi.mysql.repo.ContactRepository;
import com.example.demo.multi.postgre.model.Employees;
import com.example.demo.multi.postgre.repo.EmployeesRepository;

@Service
public class MultiDBService {
	
	@Autowired
	private EmployeesRepository eRepo;
	@Autowired
	private ContactRepository cRepo;
	
	public String getAllDataFromMultipleDB() {
		List<Employees> e = eRepo.findAll();
		List<Contact> c = cRepo.findAll();
		
		return e.toString() + "\n\n" + c.toString();
	}
	
}
