package com.info.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.info.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer"
				+ " order by lastName",
				Customer.class);
		// execute auery and get result set
		
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int customerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer customer = currentSession.get(Customer.class,customerId);
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Customer> theQuery = currentSession.createQuery("delete from Customer where id = :customerId");
		theQuery.setParameter("customerId", customerId);
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomer(String searchKeyword) {
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println(searchKeyword);
		Query<Customer> theQuery = currentSession.createQuery("from Customer where lastName like :searchKeyword", Customer.class);
		theQuery.setParameter("searchKeyword", "%"+searchKeyword+"%");
		List<Customer> customerList = theQuery.getResultList();
		System.out.println(customerList);
		return customerList;
	}

}
