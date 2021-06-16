package com.example.demo.mybatisTest1.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.mybatisTest1.mapper.EmployeesMapper;
import com.example.demo.mybatisTest1.model.Employees;

@Service
public class EmployeeService {
	private static Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	private EmployeesMapper employeesMapper;
	
	@Autowired
	private void employeeMapper(EmployeesMapper employeesMapper) {

		this.employeesMapper = employeesMapper;
	}
	
	public List<Employees> getAllEmployees() {
        return (List<Employees>) employeesMapper.selectByExample(null);
    }
	
	@Async("asyncExecutor")
	public CompletableFuture<List<Employees>> getAllEmployeesAsynch() throws InterruptedException  {
		
		log.info("getEmployeesAsynch Starts");
		List<Employees> employeelist = employeesMapper.selectByExample(null);
		log.info("employeelistData, {}", employeelist);
		Thread.sleep(1000L);	//Intentional delay
		log.info("employeelistData completed");
        return CompletableFuture.completedFuture(employeelist);
    }
	
	 public Employees getEmployeeById(Long employeeId) {
	    Employees employee = employeesMapper.selectByPrimaryKey(employeeId);
	    return employee;
	 }
	 
	 @Async("asyncExecutor")
		public CompletableFuture<Employees> getEmployeeByIdAsynch(Long employeeId) throws InterruptedException  {
			
			log.info("getEmployeesByIdAsynch Starts");
			Employees employee = employeesMapper.selectByPrimaryKey(employeeId);
			log.info("employeeData, {}", employee);
			Thread.sleep(1000L);	//Intentional delay
			log.info("employeeData completed");
	        return CompletableFuture.completedFuture(employee);
	    }

	 public int createEmployee(Employees employee) {
	    return employeesMapper.insert(employee);
	 }
	 
	 public Employees updateEmployee(Long employeeId, Employees employeeDetails) {
	    	Employees employee = employeesMapper.selectByPrimaryKey(employeeId);
	        employee.setEmailAddress(employeeDetails.getEmailAddress());
	        employee.setLastName(employeeDetails.getLastName());
	        employee.setFirstName(employeeDetails.getFirstName());
	        int updated = employeesMapper.updateByPrimaryKey(employee);
	        return employee;
	 }

	 public void deleteEmployee(Long employeeId) {
	    int deleted = employeesMapper.deleteByPrimaryKey(employeeId);
	 }
	 
	 @Async("asyncExecutor")
		public CompletableFuture<String> getEmployeeTest() throws InterruptedException  {
			String test = "test123";
			log.info("getEmployeesTEST Starts");
			log.info("employeeTEST, {}", test);
			Thread.sleep(1000L);	//Intentional delay
			log.info("employeeTEST completed");
	        return CompletableFuture.completedFuture(test);
	    }

}
