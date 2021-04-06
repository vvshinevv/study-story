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
    public Long deposit(Long accountId, Long amount) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(RuntimeException::new);

        Long currBalance = account.getBalance();
        account.setBalance(currBalance + amount);
        accountRepository.save(account);
        return currBalance + amount;
    }
}
