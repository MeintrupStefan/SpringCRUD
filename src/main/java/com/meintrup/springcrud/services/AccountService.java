package com.meintrup.springcrud.services;

import com.meintrup.springcrud.entities.Account;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

public interface AccountService {
    public Account storeAccount(Account acc);

    public Account findAccount(Long id) throws NoSuchElementException;

    @Transactional
    public boolean transferMoney(Account sender, Account receiver, BigDecimal amount);
}
