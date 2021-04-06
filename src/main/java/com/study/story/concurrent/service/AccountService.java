package com.study.story.concurrent.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface AccountService {

    void deposit(Long accountId, Long amount);

}
