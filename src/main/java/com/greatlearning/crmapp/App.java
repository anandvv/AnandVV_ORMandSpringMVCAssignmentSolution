package com.greatlearning.crmapp;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {

		Configuration conf = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class);
		
		SessionFactory sessionFactory = conf.buildSessionFactory();
		
		CustomerService customerService = new CustomerServiceImpl(sessionFactory);
		
		String firstName = "Anand";
		String emailAddress = "anandvv@foo.com";
		
		List<Customer> customers = customerService.searchBy(firstName, emailAddress);
		for(Customer customer: customers) {
			System.out.println(customer);
		}
	}
}
