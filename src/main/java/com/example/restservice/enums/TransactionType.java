package com.example.restservice.enums;

public enum TransactionType {
	DEBIT("debit"),
	CREDIT("credit");
	
	public final String value;
	
	private TransactionType(String value) {
		this.value = value;
	}
}
