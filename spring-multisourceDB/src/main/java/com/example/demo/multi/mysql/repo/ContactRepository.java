package com.example.demo.multi.mysql.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.multi.mysql.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

}
