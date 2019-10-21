package com.info.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.info.springdemo.dao.CustomerDAO;
import com.info.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		if(!customer.getLastName().isEmpty()) {
			customerDAO.saveCustomer(customer);
		}
		
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int customerId) {
		// TODO Auto-generated method stub
		Customer customer = customerDAO.getCustomer(customerId);
		
		return customer;
		
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerId) {
		customerDAO.deleteCustomer(customerId);
		
	}

	@Override
	@Transactional
	public List<Customer> searchCustomer(String searchKeyword) {
		
		List<Customer> customerList = customerDAO.searchCustomer(searchKeyword);
		
		return customerList;
	}

}
