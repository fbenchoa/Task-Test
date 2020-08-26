package com.example.dao;

import java.math.BigDecimal;


import org.springframework.stereotype.Component;

import com.example.models.Account;

/**
 * Simulated DAO without a DB
 * @author FB_E90479
 *
 */
@Component
public class AccountDAO {
	
	public Account getAccountForSingleUser() {
		return Account.builder().accountBalance(new BigDecimal("100")).clientLastname("Benchoa").clientName("Franco")
				.build();
	}
	
	public void updateAccountForSingleUser(Account account) {
	}
	
	
}
