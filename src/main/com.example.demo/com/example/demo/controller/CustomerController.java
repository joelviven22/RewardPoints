package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController()
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {

		return ResponseEntity.ok(customerService.findCustomerById(id));
	}

	@GetMapping("/customer/totalpoints/{id}")
	public ResponseEntity<Object> getTotalPointsCustomerById(@PathVariable Long id) {

		return ResponseEntity.ok(customerService.calculateTotalCustomerPoints(id));
	}

	@GetMapping("/customer/monthlypoints/{id}/{month}/{year}")
	public ResponseEntity<Object> getMonthlyPointsCustomerById(@PathVariable Long id, @PathVariable int month,
			@PathVariable int year) {

		return ResponseEntity.ok(customerService.calculateMonthlyCustomerPoints(id, month, year));
	}

}
