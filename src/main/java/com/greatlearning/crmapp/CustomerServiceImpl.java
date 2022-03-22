package com.greatlearning.crmapp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerServiceImpl implements CustomerService {
	
	
	private SessionFactory sessionFactory;
	private Session session;
	
	
	public CustomerServiceImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		this.session = this.sessionFactory.openSession();
	}

	public List<Customer> findAll() {
		
		Transaction tx = session.beginTransaction();
		
		// from "EntityName"
		List<Customer> customers = session.createQuery("from Customer", Customer.class).list();
		
		tx.commit();
		
		return customers;
	}

	public List<Customer> searchBy(String firstName, String emailAddress) {
		
		Transaction tx = session.beginTransaction();
		
		String query = "";
		
		if(firstName.length() != 0 && emailAddress.length() != 0) {
			query = "from Customer where firstName like '%" + firstName + "%' or emailAddress like '%" + emailAddress + "%'";
		} else if(firstName.length() != 0) {
			query = "from Customer where firstName like '%" + firstName + "%'";
		} else if(emailAddress.length() != 0) {
			query = "from Customer where emailAddress like '%" + emailAddress + "%'";
		} else {
			System.out.println("No records");
		}
		
		List<Customer> customers = session.createQuery(query,Customer.class).list();
		tx.commit();
		
		return customers;
	}

	public Customer findById(int id) {
		Transaction tx = session.beginTransaction();
		Customer customer = session.get(Customer.class,id);
		
		tx.commit();
		return customer;
	}

	public void save(Customer customer) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
	}

	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();
		
		try {
		Customer customer = session.get(Customer.class, id);
		session.delete(customer);
		} finally {
			tx.commit();
		}
		
	}

}