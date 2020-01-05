package com.example.demo.domain.metoring;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.AccountRepository;
import com.example.demo.service.dto.MentoringResponseDto;
import com.example.demo.service.exception.AlreadyExistsDataException;
import com.example.demo.service.exception.NotFoundDataException;
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
        List<Mentoring> result = em.createQuery("select m from Mentoring m join fetch m.mento", Mentoring.class)
                .getResultList();
        return result.stream()
                .map(m -> MentoringResponseDto.withMentoring(m))
                .collect(Collectors.toList());
    }

    public MentoringResponseDto getMentoring(Long id) {
        String qlString = "select m from Mentoring m join fetch m.mento where m.id=:id";
        Mentoring mentoring = em.createQuery(qlString, Mentoring.class)
                .setParameter("id", id)
                .getSingleResult();
        MentoringResponseDto mentoringResponseDto = MentoringResponseDto.withMentoring(mentoring);

        // 작성자가 아닐경우 멘토링 신청현황을 보여주지 않는다.
        if (!mentoringResponseDto.getMento().getId().equals(id)) {
            mentoringResponseDto.getMentees().clear();
        }
        return mentoringResponseDto;
    }

    public void addMentee(Long id, String email) {

        String qlString = "select m from Mentoring m join fetch m.mento where m.id=:id";
        Mentoring mentoring = em.createQuery(qlString, Mentoring.class)
                .setParameter("id", id)
                .getSingleResult();
        Account account = accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new);

        if (mentoring.getMento().equals(account)) {
            throw new IllegalArgumentException("자신이 개설한 멘토링은 신청할 수 없습니다.");
        }

        // N+1 발생할 것 으로 예상됨.
        for (Account mentee : mentoring.getMentees()) {
            if (mentee.getEmail().equals(email)) {
                throw new AlreadyExistsDataException("이미 신청한 유저입니다.");
            }
        }

        mentoring.getMentees().add(account);
    }

    public void save(Mentoring mentoring) {
        em.persist(mentoring);
    }
}
