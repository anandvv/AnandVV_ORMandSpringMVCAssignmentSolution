package com.greatlearning.crmapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		
		List<Customer> customers = customerService.findAll();
		theModel.addAttribute("customers", customers);
		return "list-customers"; // /WEB-INF/views/list-students.jsp
		
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormforAdd(Model theModel) {
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("Customer",theCustomer);
		
		return "Customer-form";
		
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormforUpdate(@RequestParam("customerId") int id, Model theModel) {
		Customer theCustomer = customerService.findById(id);
		
		theModel.addAttribute("Customer",theCustomer);
		
		return "Customer-form";
		
	}
	
	
	
	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName, 
			@RequestParam("emailAddress") String emailAddress) {
	
		System.out.println(id);
		
	Customer theCustomer;
	
	if(id!=0) {
		theCustomer  = customerService.findById(id);
		theCustomer.setFirstName(firstName);
		theCustomer.setLastName(lastName);
		theCustomer.setEmailAddress(emailAddress);
	}
	else
		theCustomer = new Customer(firstName, lastName, emailAddress);
	customerService.save(theCustomer);
	
	return "redirect:/customers/list";
	
}
	
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {

		// delete the Student
		customerService.deleteById(theId);

		// redirect to /students/list
		return "redirect:/customers/list";

	}
}