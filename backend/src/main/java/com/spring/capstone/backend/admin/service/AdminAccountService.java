package com.spring.capstone.backend.admin.service;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.domain.article.Article;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AdminAccountService {

    private AccountRepository accountRepository;

    public AdminAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Page<Account> getAccountList(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(NotFoundDataException::new);
    }

    public long getAccountSize() {
        return accountRepository.count();
    }



}
