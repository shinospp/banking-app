package com.spp.banking_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spp.banking_app.dto.AccountDto;
import com.spp.banking_app.repository.AccountRepository;
import com.spp.banking_app.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {


	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	// Add Account REST API
	@PostMapping("/create")
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {

		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}

	// Get Account REST API
	@GetMapping("/{id}/fetchById")
	public ResponseEntity<?> getAccountById(@PathVariable Long id) {
//
//		AccountDto accountDto = null;
//		try {
//			accountDto = accountService.getAccountById(id);
//			return ResponseEntity.ok(accountDto);
//		} catch (RuntimeException e) {
//			// Example: account not found or insufficient balance
//			return ResponseEntity.status(400).body(e.getMessage());
//		} catch (Exception e) {
//			return ResponseEntity.status(500).body("Internal server error.");
//		}
		
		AccountDto accountDto = accountService.getAccountById(id);
			return ResponseEntity.ok(accountDto);

	}

	// Deposit Account REST API
	@PatchMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		Double amount = request.get("amount");
		AccountDto deposit = accountService.deposit(id, amount);

		return ResponseEntity.ok(deposit);

	}

	// Withdraw Account REST API
	@PatchMapping("/{id}/withdraw")
	public ResponseEntity<?> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		Double amount = request.get("amount");
		AccountDto withdraw = accountService.withdraw(id, amount);
		return ResponseEntity.ok(withdraw);
		
//		try {
//			withdraw = accountService.withdraw(id, amount);
//			return ResponseEntity.ok(withdraw);
//
//		} catch (RuntimeException e) {
//			// Example: account not found or insufficient balance
//			return ResponseEntity.status(400).body(e.getMessage());
//		} catch (Exception e) {
//			return ResponseEntity.status(500).body("Internal server error.");
//		}

	}
	
	//Get All Accounts REST API
	@GetMapping("/getAll")
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		List<AccountDto> allAccounts = accountService.getAllAccounts();

		return ResponseEntity.ok(allAccounts);
	}
	
	//Delete Account REST API
	@DeleteMapping("/{id}/remove")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
//		try {
//			accountService.deleteAccount(id);
//			return ResponseEntity.ok("Account deleted successfully!");
//		} catch (RuntimeException e) {
//			// Example: account not found
//			return ResponseEntity.status(400).body(e.getMessage());
//		} catch (Exception e) {
//			return ResponseEntity.status(500).body("Internal server error.");
//		}
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account deleted successfully!");

	}

}
