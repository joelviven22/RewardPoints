package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Purchase;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PurchaseRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	PurchaseRepository purchaseRepository;

	public Customer findCustomerById(Long id) {

		Customer customer = customerRepository.findById(id).orElse(null);

		return customer;

	}

	public int calculateTotalCustomerPoints(Long id) {
		Customer customer = findCustomerById(id);
		List<Purchase> purchases = getPurchaseListforCustomer(id);
		int points = calculateCustomerPoint(customer, purchases);
		updatePoints(id, points);
		return points;

	}

	public int calculateMonthlyCustomerPoints(Long id, int month, int year) {
		Customer customer = findCustomerById(id);
		List<Purchase> purchases = getPurchaseListforCustomer(id);
		List<Purchase> monthPurchase = new ArrayList<Purchase>();

		for (Purchase p : purchases) {
			LocalDate date = p.getDate();
			if (date.getMonthValue() == month && date.getYear() == year)
				monthPurchase.add(p);
		}

		int points = calculateCustomerPoint(customer, monthPurchase);
		updatePoints(id, points);
		return points;
	}

	public int calculateCustomerPoint(Customer customer, List<Purchase> purchases) {
		int prevPoint = customer.getPoint();
		int newPoint = 0;

		for (Purchase p : purchases) {

			int spent = p.getAmount();

			if (spent > 100) {
				newPoint = (int) (spent - 100) * 2 + 50 + prevPoint;
			}
			if (spent > 50 && spent < 100) {
				newPoint = prevPoint + (int) (spent - 50);
			}

			prevPoint = newPoint;
		}

		return newPoint;

	}

	public List<Purchase> getPurchaseListforCustomer(Long id) {

		List<Purchase> purchaseList = purchaseRepository.findByCustomerId(id);

		return purchaseList;
	}

	public void updatePoints(Long id, int points) {

		// Update the points for customer
		/*
		 * Customer customer = findCustomerById(id); customer.setPoint(points);
		 * customerRepository.save(customer);
		 */
	}

}
