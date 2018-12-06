package com.demo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.demo.pojo.Customer;
import com.demo.pojo.CustomerRepository;

@Service
public class CustomerService {

	@Inject
	private CustomerRepository cr;
	
	public List<Customer> findAll(){
		return cr.findAll();
	}
	
	public List<Customer> findByFirstName(String firstName) {
		return cr.findByFirstName(firstName);
	}
	
	public void save(Customer customer) {
		cr.saveAndFlush(customer);
	}
	
}
