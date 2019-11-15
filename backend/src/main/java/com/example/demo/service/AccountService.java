package com.example.demo.service;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.AccountAdapter;
import com.example.demo.domain.accounts.AccountRepository;
import com.example.demo.service.dto.AccountRequestDto;
import com.example.demo.service.dto.AccountResponseDto;
import com.example.demo.service.exception.NotFoundDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    @Transactional
    public void changeImgSrc(String email, String imgSrc) {
        Account account = accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new);
        if (Objects.nonNull(account.getImgSrc())) {
            // 버킷에서 기존 이미지 삭제
            return;
        }

        account.setImgSrc(imgSrc);
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