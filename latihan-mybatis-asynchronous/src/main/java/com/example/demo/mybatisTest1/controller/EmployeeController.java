package com.example.demo.mybatisTest1.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mybatisTest1.model.Employees;
import com.example.demo.mybatisTest1.service.EmployeeService;

@RestController
@RequestMapping("/mybatis")
public class EmployeeController {
	
	private static Logger log = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
    public String Hello() {
        return "Test Mybatis";
    }
	
	 @GetMapping("/employees")
	    public List<Employees> getAllEmployees() {
	        return (List<Employees>) employeeService.getAllEmployees();
	    }
	 
	 @GetMapping("/employeesAsynch")
	    public void getAllEmployeesAsynch() throws InterruptedException, ExecutionException {
		 	log.info("testAsynch Start");
		 	Long id = (long) 13;
			CompletableFuture<List<Employees>> employeelist = employeeService.getAllEmployeesAsynch();
			CompletableFuture<Employees> employees = employeeService.getEmployeeByIdAsynch(id);
			CompletableFuture<String> test = employeeService.getEmployeeTest();
			// Wait until they are all done
			CompletableFuture.allOf(employeelist, employees, test).join();
			
			log.info("Employeelist--> " + employeelist.get()); 
			log.info("Employee--> " + employees.get());
			log.info("Employee Test--> " + test.get());
	    }

	    @GetMapping("/employees/{id}")
	    public ResponseEntity<Employees> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
	        Employees employee = employeeService.getEmployeeById(employeeId);
	        return ResponseEntity.ok().body(employee);
	    }
	    
	    @PostMapping(value = "/employees", consumes = { "application/json", "application/xml" })
	    public String createEmployee(@Valid @RequestBody Employees employee) {
	        int insert = employeeService.createEmployee(employee);
	        return "insert success";
	    }
	    
	    @PutMapping(value = "/employees/{id}", consumes = { "application/json", "application/xml" })
	    public ResponseEntity<Employees> updateEmployee(@PathVariable(value = "id") Long employeeId,
	         @Valid @RequestBody Employees employeeDetails) {
	        Employees updated = employeeService.updateEmployee(employeeId, employeeDetails);
	        return ResponseEntity.ok(updated);
	    }

	    @DeleteMapping("/employees/{id}")
	    public String deleteEmployee(@PathVariable(value = "id") Long employeeId) {
	        employeeService.deleteEmployee(employeeId);
	        return "delete success";
	    }

}
