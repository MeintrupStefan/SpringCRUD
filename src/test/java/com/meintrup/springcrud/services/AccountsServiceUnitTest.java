package com.meintrup.springcrud.services;

import com.meintrup.springcrud.entities.Account;
import com.meintrup.springcrud.repositories.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountsServiceUnitTest {
    @Test
    @DisplayName("Test if transferring money works when it should.")
    void transferMoneyHappyFlow() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountServiceImpl(accountRepository);

        Account sender = new Account();
        sender.setId(1L);
        sender.setBalance(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2L);
        receiver.setBalance(new BigDecimal(1000));

        boolean success = accountService.transferMoney(sender, receiver, new BigDecimal(100));

        assertTrue(success);

        verify(accountRepository).save(
                receiver.toBuilder()
                      .balance(new BigDecimal(1100))
                      .build()
        );

        verify(accountRepository).save(
                sender.toBuilder()
                      .balance(new BigDecimal(900))
                      .build()
        );
    }


    @Test
    @DisplayName("Test if not saving when exception is thrown.")
    void transferMoneyAccountNotFoundFlow() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountServiceImpl(accountRepository);
        Account sender = new Account();
        sender.setId(1L);
        sender.setBalance(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2L);
        receiver.setBalance(new BigDecimal(1000));

        given(accountRepository.save(
                sender.toBuilder()
                        .balance(new BigDecimal(900))
                        .build()
        )).willThrow(IllegalArgumentException.class);

        assertThrows(
                IllegalArgumentException.class,
                () -> accountService.transferMoney(sender, receiver, new BigDecimal(100))
        );

        verify(accountRepository, never()).save(
                receiver.toBuilder()
                        .balance(new BigDecimal(900))
                        .build()
        );
    }
}