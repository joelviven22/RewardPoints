package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Purchase {

	@Id
	private int purchaseId;
	private Long customerId;
	private int amount;
	private LocalDate date;

	public Purchase() {
	}

	public Purchase(int purchaseId, Long customerId, int amount, LocalDate date) {
		super();
		this.purchaseId = purchaseId;
		this.customerId = customerId;
		this.amount = amount;
		this.date = date;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

}
