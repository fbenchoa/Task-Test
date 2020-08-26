package com.example.restservice;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Transaction;
import com.example.models.TransactionBody;
import com.example.service.TransactionService;

@RestController
public class TransactionRESTController {

	private TransactionService transactionService = new TransactionService();

	@PostMapping(value = "/transaction")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Object> doTransaction(@RequestBody TransactionBody request) throws Exception {

		try {
			Transaction transaction = transactionService.manageTransaction(request);
			return new ResponseEntity<Object>(transaction, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/transaction/history")
	@ResponseBody
	public ResponseEntity<Object> getTransactionsHistory() {
		List<Transaction> transactions = transactionService.getAllTransactions();
		return new ResponseEntity<Object>(transactions, HttpStatus.OK);
	}
	
	@GetMapping(value = "/transaction")
	@ResponseBody
	public ResponseEntity<Object> getTransactionById(@RequestParam(value = "transactionId") long id) {
		Transaction transactions = transactionService.getTransactionById(id);
		return new ResponseEntity<Object>(transactions, HttpStatus.OK);
	}

}
