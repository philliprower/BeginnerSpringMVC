/**
 * 
 */
package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;

/**
 * @author prower
 *
 */
@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	/* (non-Javadoc)
	 * @see com.packt.webstore.domain.repository.CustomerRepository#getAllCustomers()
	 */
	public List<Customer> getAllCustomers() {
		List<Customer> results = new ArrayList<Customer>();
		Customer c1 = new Customer();
		c1.setAddress("1234 Main St, USA");
		c1.setCustomerId("C1");
		c1.setName("Top Dog");
		c1.setNoOfOrdersMade(12);
		results.add(c1);
		Customer c2 = new Customer();
		c2.setAddress("432 Washington St, USA");
		c2.setCustomerId("C3");
		c2.setName("Omega Dog");
		c2.setNoOfOrdersMade(2);
		results.add(c2);
		return results;
	}

}
