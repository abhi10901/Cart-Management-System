package com.mycompany.ppms.dao;

import com.mycompany.ppms.entity.Account;

public interface AccountDAO {

	public Account findAccountById(Long id);
	public Account storeAccount(Account account);
	public boolean removeAccountById(Long id);
}
