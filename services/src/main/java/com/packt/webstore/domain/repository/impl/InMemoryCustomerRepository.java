/**
 * 
 */
package com.packt.webstore.domain.repository.impl;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prower
 *
 */
@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	/* (non-Javadoc)
	 * @see CustomerRepository#getAllCustomers()
	 */
	public List<Customer> getAllCustomers() {
		List<Customer> results = new ArrayList<Customer>();
		Customer c1 = new Customer();
		Address a = new Address();
		a.setStreetName("Main St.");
		a.setDoorNo("1234");
		a.setState("MI");
		a.setAreaName("Detroit");
		a.setZipCode("44000");
		c1.setBillingAddress(a);
		c1.setCustomerId("C1");
		c1.setName("Top Dog");
		results.add(c1);
		Customer c2 = new Customer();
		Address a2 = new Address();
		a2.setStreetName("Washington St.");
		a2.setDoorNo("432");
		a2.setState("MI");
		a2.setAreaName("Detroit");
		a2.setZipCode("44000");
		c1.setBillingAddress(a2);
		c2.setCustomerId("C3");
		c2.setName("Omega Dog");
		results.add(c2);
		return results;
	}

}
