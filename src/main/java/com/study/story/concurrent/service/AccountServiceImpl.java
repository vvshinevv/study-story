package com.study.story.concurrent.service;

import com.study.story.concurrent.entity.Account;
import com.study.story.concurrent.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void deposit(Long accountId, Long amount) {
        Account account = accountRepository.findByAccountId(accountId);

        Long currBalance = account.getBalance();
        System.out.println("thread = " + Thread.currentThread().getName() + ", " + "currBalance = " + currBalance);
        account.setBalance(currBalance + amount);
        System.out.println("thread = " + Thread.currentThread().getName() + ", " + "currBalance = " + (currBalance + amount));
        accountRepository.save(account);
    }

    @Transactional
    public void withdraw(Long accountId, Long amount) {
        Account account = accountRepository.findByAccountId(accountId);
        Long currentBalance = account.getBalance();
        System.out.println("감소thread = " + Thread.currentThread().getName() + ", " + "currBalance = " + currentBalance);
        if (currentBalance - amount < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다");
        }
        account.setBalance(currentBalance - amount);
        System.out.println("감소thread = " + Thread.currentThread().getName() + ", " + "currBalance = " + (currentBalance - amount));
        accountRepository.save(account);
    }
}
