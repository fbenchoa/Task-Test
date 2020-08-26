package com.example.dao;

import java.util.ArrayList;
import java.util.List;


import com.example.models.Transaction;

public class TransactionDAO {
	public List<Transaction> getAll() {
		// GET FROM DB
		return new ArrayList<>();
	}
	
	public void saveTransaction(Transaction transaction) {
		// SAVE INTO DB
	}
	
	public Transaction getById(long id) {
		return new Transaction();
	}
}
