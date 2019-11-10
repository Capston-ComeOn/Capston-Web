package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.domain.metoring.Mentoring;
import com.spring.capstone.backend.domain.metoring.MentoringRepository;
import com.spring.capstone.backend.service.dto.MentoringRequestDto;
import com.spring.capstone.backend.service.dto.MentoringResponseDto;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MentoringService {

    private AccountRepository accountRepository;
    private MentoringRepository mentoringRepository;

    public MentoringService(AccountRepository accountRepository, MentoringRepository mentoringRepository) {
        this.accountRepository = accountRepository;
        this.mentoringRepository = mentoringRepository;
    }

    public MentoringResponseDto getMentoring(Long id) {
        return mentoringRepository.getMentoring(id);
    }

    public List<MentoringResponseDto> getMentoringList() {
        return mentoringRepository.getMentorings();
    }

    @Transactional
    public Long save(String email, MentoringRequestDto mentoringRequestDto) {
        Account mento = accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new);
        Mentoring mentoring = Mentoring.of(mento, mentoringRequestDto);
        mentoringRepository.save(mentoring);
        return mentoring.getId();
    }
}
