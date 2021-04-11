package com.info.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.info.springdemo.entity.Customer;
import com.info.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	//need to inject the service
	
	@Autowired
	private CustomerService customerService;
	//kk
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		//get the customers from the dao
		List<Customer> theCustomers = customerService.getCustomers();
		theModel.addAttribute("customers", theCustomers);
		//This line was added by me
		//add the customers to the model
		Ssytem.out.println();
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		Customer customer = new Customer();
		theModel.addAttribute("customer", customer);
		return "showFormForAdd";
		
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId")int customerId, Model theModel) {
		Customer customer = customerService.getCustomer(customerId);
		theModel.addAttribute(customer);
		return "showFormForAdd";
	}
	
	//something added
	
	@GetMapping("/searchCustomer")
	public String searchCustomer(@RequestParam("searchValue")String searchKeyword, Model theModel) {
		
		List<Customer> customers = customerService.searchCustomer(searchKeyword);
		theModel.addAttribute("customers",customers);
		
		return "list-customers";
	}
	
	//new added
	
	
	
}
