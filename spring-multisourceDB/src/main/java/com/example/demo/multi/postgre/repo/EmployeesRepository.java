package com.example.demo.multi.postgre.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.multi.postgre.model.Employees;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long>{

}
