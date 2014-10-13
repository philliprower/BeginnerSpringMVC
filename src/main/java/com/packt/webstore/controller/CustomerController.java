package com.packt.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/customers")
	public String list(Model model) {
			List<Customer> customers = customerService.getAllCustomers();
			model.addAttribute("customers", customers);
			return "customers";
	}
}
