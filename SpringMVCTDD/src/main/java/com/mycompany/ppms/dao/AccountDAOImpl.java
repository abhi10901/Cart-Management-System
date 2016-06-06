package com.mycompany.ppms.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.ppms.entity.Account;
import com.mycompany.ppms.exception.EntityObjectNotFoundException;

@Repository("accountDAO")
@Transactional(readOnly = true)
public class AccountDAOImpl implements AccountDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Account findAccountById(Long id) {
		Account account  = entityManager.find(Account.class, id);
		return account;
	}

	@Override
	public Account storeAccount(Account account) {
		Account newAccount = null;
		try {
			newAccount = entityManager.merge(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newAccount;
	}

	@Override
	public boolean removeAccountById(Long id) {
		Account accountToRemove = findAccountById(id);
		
		if(null == accountToRemove) {
			throw new EntityObjectNotFoundException(id, "Account");
		}
		try {
			entityManager.remove(accountToRemove);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
