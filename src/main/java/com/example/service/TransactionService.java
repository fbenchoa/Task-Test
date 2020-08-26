package com.example.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AccountDAO;
import com.example.dao.TransactionDAO;
import com.example.models.Account;
import com.example.models.Transaction;
import com.example.models.TransactionBody;
import com.example.restservice.enums.TransactionType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class TransactionService {
	
	private AccountDAO accountDAO = new AccountDAO();
	private TransactionDAO transactionDAO = new TransactionDAO();
	
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
	
	public Transaction manageTransaction(TransactionBody request) throws Exception {
		
		Account account = this.accountDAO.getAccountForSingleUser();

		if(request.getType().equals(TransactionType.CREDIT.value)) {
			return this.doCredit(request.getNumber(), account);
		}
		
		if(request.getType().equals(TransactionType.DEBIT.value)) {
			return this.doDebit(request.getNumber(), account);
		}
		
		throw new Exception("Invalid transaction type");
		
	}
	
	public List<Transaction> getAllTransactions() {
		return this.transactionDAO.getAll();
	}
	
	public Transaction getTransactionById(long id) {
		return this.transactionDAO.getById(id);
	}
	
	
	public Transaction doCredit(BigDecimal value, Account account) {
		account.setAccountBalance(account.getAccountBalance().add(value));
		
		Transaction transaction = new Transaction();
		transaction.setId(ID_GENERATOR.getAndIncrement());
		transaction.setDate(Date.from(Instant.now()).toString());
		transaction.setType(TransactionType.CREDIT.value);
		transaction.setNumber(account.getAccountBalance());
		
		this.transactionDAO.saveTransaction(transaction);
		this.accountDAO.updateAccountForSingleUser(account);
		
		return transaction;
	}
	
	public Transaction doDebit(BigDecimal value, Account account) throws Exception {
		
		if(account.getAccountBalance().compareTo(value) < 0) {
			throw new Exception("insuficient founds in account. Account balance: "+account.getAccountBalance());
		}
		
		account.setAccountBalance(account.getAccountBalance().subtract(value));
		Transaction transaction = new Transaction();
		transaction.setId(ID_GENERATOR.getAndIncrement());
		transaction.setDate(Date.from(Instant.now()).toString());
		transaction.setType(TransactionType.DEBIT.value);
		transaction.setNumber(account.getAccountBalance());

		this.transactionDAO.saveTransaction(transaction);
		this.accountDAO.updateAccountForSingleUser(account);
		
		return transaction;	
		
	}
	
}
