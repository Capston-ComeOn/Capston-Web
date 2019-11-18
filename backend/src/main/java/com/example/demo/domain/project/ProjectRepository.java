package com.example.demo.domain.project;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.AccountRepository;
import com.example.demo.service.dto.ProjectResponseDto;
import com.example.demo.service.exception.AlreadyExistsDataException;
import com.example.demo.service.exception.NotFoundDataException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProjectRepository {

    AccountRepository accountRepository;
    @PersistenceContext
    private EntityManager em;

    public ProjectRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<ProjectResponseDto> getProjectList() {
        List<Project> result = em.createQuery("select p from Project p join fetch p.account", Project.class)
                .getResultList();
        return result.stream()
                .map(p -> ProjectResponseDto.withProject(p))
                .collect(Collectors.toList());
    }

    public ProjectResponseDto getProject(Long id) {
        String qlString = "select p from Project p join fetch p.account where p.id=:id";
        Project project = em.createQuery(qlString, Project.class)
                .setParameter("id", id)
                .getSingleResult();
        ProjectResponseDto projectResponseDto = ProjectResponseDto.withProject(project);

        // 작성자가 아닐경우 멘토링 신청현황을 보여주지 않는다.
        if (!projectResponseDto.getAccount().getId().equals(id)) {
            projectResponseDto.getApplicant().clear();
        }
        return projectResponseDto;
    }

    public void apply(Long id, String email) {

        String qlString = "select p from Project p join fetch p.account where p.id=:id";
        Project project = em.createQuery(qlString, Project.class)
                .setParameter("id", id)
                .getSingleResult();
        Account account = accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new);

        if (project.getAccount().equals(account)) {
            throw new IllegalArgumentException("자신이 개설한 프로젝트&공모전은 신청할 수 없습니다.");
        }

        // N+1 발생할 것 으로 예상됨.
        for (Account applicant : project.getApplicant()) {
            if (applicant.getEmail().equals(email)) {
                throw new AlreadyExistsDataException("이미 신청한 유저입니다.");
            }
        }

        project.getApplicant().add(account);
    }

    public void save(Project project) {
        em.persist(project);
    }
}
