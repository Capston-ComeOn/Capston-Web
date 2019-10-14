package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountAdapter;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.domain.accounts.AccountVO;
import com.spring.capstone.backend.service.assembler.AccountAssembler;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account saveAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new AccountAdapter(account);
    }

    @Transactional
    public long save(AccountVO accountDto) {
        Account account = AccountAssembler.toEntity(accountDto);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account).getId();
    }

    public Account getAccountWithEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new);
    }

    public List<Account> searchWithName(String name) {
        return accountRepository.findByNameContaining(name);
    }
}