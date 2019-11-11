package com.example.demo.service;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.AccountRepository;
import com.example.demo.domain.metoring.Mentoring;
import com.example.demo.domain.metoring.MentoringRepository;
import com.example.demo.service.dto.MentoringRequestDto;
import com.example.demo.service.dto.MentoringResponseDto;
import com.example.demo.service.exception.NotFoundDataException;
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

    @Transactional
    public void addMentee(String email, Long id) {
        mentoringRepository.addMentee(id, email);
    }

}
