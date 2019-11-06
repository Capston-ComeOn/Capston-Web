package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.domain.metoring.Mentoring;
import com.spring.capstone.backend.domain.metoring.MentoringRepository;
import com.spring.capstone.backend.service.dto.MentoringDto;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentoringService {

    private AccountRepository accountRepository;
    private MentoringRepository mentoringRepository;

    public MentoringService(AccountRepository accountRepository, MentoringRepository mentoringRepository) {
        this.accountRepository = accountRepository;
        this.mentoringRepository = mentoringRepository;
    }

    public Mentoring getMentoring(Long id) {
        return mentoringRepository.findById(id).orElseThrow(NotFoundDataException::new);
    }

    public List<Mentoring> getMentoringList() {
        return mentoringRepository.findAll();
    }

    public void save(String email, MentoringDto mentoringDto) {
        Account mento = accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new);
        Mentoring mentoring = Mentoring.of(mento, mentoringDto);
        mentoringRepository.save(mentoring);
    }

}
