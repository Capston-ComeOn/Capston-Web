package com.example.demo.service;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.AccountRepository;
import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectRepository;
import com.example.demo.service.dto.ProjectRequestDto;
import com.example.demo.service.dto.ProjectResponseDto;
import com.example.demo.service.exception.NotFoundDataException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final AccountRepository accountRepository;

    public ProjectService(ProjectRepository projectRepository, AccountRepository accountRepository) {
        this.projectRepository = projectRepository;
        this.accountRepository = accountRepository;
    }

    public List<ProjectResponseDto> getProjectList() {
        return projectRepository.getProjectList();
    }

    public ProjectResponseDto getProject(Long id) {
        return projectRepository.getProject(id);
    }

    @Transactional
    public void apply(String email, Long id) {
        projectRepository.apply(id, email);
    }

    @Transactional
    public Long save(String email, ProjectRequestDto projectRequestDto) {
        Account account = accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new);
        Project project = Project.of(account, projectRequestDto);
        projectRepository.save(project);
        return project.getId();
    }
}
