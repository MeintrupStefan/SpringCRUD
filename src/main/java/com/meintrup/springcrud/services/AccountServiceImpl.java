package com.meintrup.springcrud.services;

import com.meintrup.springcrud.entities.Account;
import com.meintrup.springcrud.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account storeAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findAccount(Long id) throws NoSuchElementException {
        return accountRepository.findById(id)
                                .orElseThrow();
    }

    @Override
    @Transactional
    public boolean transferMoney(Account sender, Account receiver, BigDecimal amount) {
        BigDecimal senderNewAmount = sender.getBalance()
                                           .subtract(amount);
        BigDecimal receiverNewAmount = sender.getBalance()
                                             .add(amount);

        accountRepository.save(
                sender.toBuilder()
                      .balance(senderNewAmount)
                      .build()
        );

        accountRepository.save(
                receiver.toBuilder()
                      .balance(receiverNewAmount)
                      .build()
        );

        return true;
    }
}
