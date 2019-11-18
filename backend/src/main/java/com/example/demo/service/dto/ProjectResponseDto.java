package com.example.demo.service.dto;

import com.example.demo.domain.project.Project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private IntroduceResponseDto introduce;
    private AccountResponseDto account;
    private List<AccountResponseDto> applicant;

    public ProjectResponseDto(Long id, String title, String content, LocalDateTime startTime, LocalDateTime endTime, IntroduceResponseDto introduce, AccountResponseDto account, List<AccountResponseDto> applicant) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.introduce = introduce;
        this.account = account;
        this.applicant = applicant;
    }

    public static ProjectResponseDto withProject(Project project) {
        return new ProjectResponseDto(
                project.getId(),
                project.getTitle(),
                project.getContent(),
                project.getStartTime(),
                project.getEndTime(),
                IntroduceResponseDto.of(project.getIntroduce()),
                AccountResponseDto.withAccount(project.getAccount()),
                project.getApplicant().stream().map(a -> AccountResponseDto.withAccount(a)).collect(Collectors.toList())
        );
    }

    public String getStartTime() {
        return startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getEndTime() {
        return endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public IntroduceResponseDto getIntroduce() {
        return introduce;
    }

    public Boolean getUse() {
        if (LocalDateTime.now().isBefore(this.endTime)) {
            return false;
        }
        return true;
    }

    public AccountResponseDto getAccount() {
        return account;
    }

    public List<AccountResponseDto> getApplicant() {
        return applicant;
    }
}
