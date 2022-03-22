package com.greatlearning.crmapp;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

	public List<Customer> findAll();
	
	public List<Customer> searchBy(String firstName, String emailAddress);
	
	public Customer findById(int id);
	
	public void save(Customer customer); // save or update
	
	public void deleteById(int id);
}
