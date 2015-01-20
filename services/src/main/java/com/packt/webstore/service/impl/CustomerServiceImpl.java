/**
 * 
 */
package com.packt.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.service.CustomerService;

/**
 * @author prower
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	/* (non-Javadoc)
	 * @see CustomerService#getAllCustomers()
	 */
	public List<Customer> getAllCustomers() {
		return customerRepository.getAllCustomers();
	}

}
