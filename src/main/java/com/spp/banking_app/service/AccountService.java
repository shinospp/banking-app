package com.spp.banking_app.service;


import java.util.List;

import com.spp.banking_app.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, Double amount);
	
	AccountDto withdraw(Long id, Double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);

}
