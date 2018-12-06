package com.demo.pojo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	List<Customer> findByFirstName(String firstName);
	
}
