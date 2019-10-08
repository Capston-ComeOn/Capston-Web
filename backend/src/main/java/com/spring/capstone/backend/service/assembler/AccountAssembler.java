package com.spring.capstone.backend.service.assembler;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRoles;
import com.spring.capstone.backend.domain.accounts.AccountVO;

import java.util.Arrays;
import java.util.HashSet;

public class AccountAssembler {

    public static Account toEntity(AccountVO accountDto) {
        return new Account(accountDto.getName(), accountDto.getEmail(), accountDto.getPassword()
                , new HashSet<>(Arrays.asList(AccountRoles.USER)));
    }
}
