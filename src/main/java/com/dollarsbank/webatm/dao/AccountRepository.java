package com.dollarsbank.webatm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dollarsbank.webatm.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String>{

	Account findByAccountId(String userId);
	Account findByAccountIdAndPassword(String userId, String password);
}
