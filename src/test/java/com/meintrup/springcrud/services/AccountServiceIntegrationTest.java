package com.meintrup.springcrud.services;

import com.meintrup.springcrud.entities.Account;
import com.meintrup.springcrud.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AccountServiceIntegrationTest {
    // In Unit Test this would be @Mock
    // The integration tests if the Construction and injection by the framework works as expected.
    // Basically only the connection between the two classes. If stuff is written in db correctly
    // should be a separate test for the repository.
    @MockBean
    private AccountRepository accountRepository;

    // In Unit Test this would be @InjectedMocks
    @Autowired
    private AccountService accountService;

    @Test
    void accountServiceTransferAmountTest() {
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
}
