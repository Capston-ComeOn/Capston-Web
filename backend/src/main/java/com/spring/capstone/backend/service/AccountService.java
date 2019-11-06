package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountAdapter;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.service.dto.AccountRequestDto;
import com.spring.capstone.backend.service.dto.AccountResponseDto;
import com.spring.capstone.backend.service.assembler.AccountAssembler;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AccountService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new AccountAdapter(account);
    }

    @Transactional
    public long save(AccountRequestDto accountRequestDto) {
        Account account = Account.of(accountRequestDto);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account).getId();
    }

    public AccountResponseDto getAccountWithEmail(String email) {
        return AccountResponseDto.withAccount(accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new));
    }

    public List<AccountResponseDto> searchWithName(String name) {
        return accountRepository.findByNameContaining(name).stream()
                .map(o -> AccountResponseDto.withAccount(o))
                .collect(Collectors.toList());
    }
}