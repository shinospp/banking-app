package com.spp.banking_app.service.impl;

import org.springframework.stereotype.Service;

import com.spp.banking_app.dto.AccountDto;
import com.spp.banking_app.entity.Account;
import com.spp.banking_app.mapper.AccountMapper;
import com.spp.banking_app.repository.AccountRepository;
import com.spp.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!!!"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, Double amount) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!!!"));

		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, Double amount) {

		// Validate input
		if (amount == null || amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
		}

		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!!!"));

		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance to withdraw");
		}
		double total = account.getBalance() - amount;
		account.setBalance(total);

		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

}
