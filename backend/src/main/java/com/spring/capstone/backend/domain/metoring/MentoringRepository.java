package com.spring.capstone.backend.domain.metoring;

import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.service.dto.MentoringResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MentoringRepository {

    AccountRepository accountRepository;

    public MentoringRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PersistenceContext
    private EntityManager em;

    public List<MentoringResponseDto> getMentorings() {
        List<Mentoring> result = em.createQuery("select m from Mentoring m join fetch m.mento where m.use=true", Mentoring.class)
                .getResultList();
        return result.stream()
                .map(m -> MentoringResponseDto.withMentoring(m))
                .collect(Collectors.toList());
    }

    public void save(Mentoring mentoring) {
        em.persist(mentoring);
    }
}
