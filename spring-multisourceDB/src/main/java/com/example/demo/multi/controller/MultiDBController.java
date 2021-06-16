package com.example.demo.multi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.multi.services.MultiDBService;

@RestController
@RequestMapping("/multi")
public class MultiDBController {
	
	@Autowired
	MultiDBService service;
	
	@GetMapping("/getall")
	public String getAllData() {
		String data = service.getAllDataFromMultipleDB();
		return data;
	}
	
}
