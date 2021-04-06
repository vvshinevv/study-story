package com.study.story.concurrent.service;

import com.study.story.concurrent.entity.Account;
import com.study.story.concurrent.repository.AccountRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class AccountServiceTest {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    private Long accountId;

    @BeforeEach
    public void setUp() {
        Account account = new Account("신한 S20");
        account = accountRepository.save(account);
        accountId = account.getAccountId();
    }

    @Test
    public void simulateWithNoRaceCondition() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                accountService.deposit(accountId, 10L);
                latch.countDown();
            });
        }

        latch.await();
        Account richAccount = accountRepository.findById(accountId).orElseThrow(RuntimeException::new);
        Assertions.assertThat(richAccount.getBalance()).isEqualTo(10 * 100);
    }
}
