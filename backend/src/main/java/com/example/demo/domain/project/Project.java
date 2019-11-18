package com.example.demo.domain.project;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.metoring.Introduce;
import com.example.demo.service.dto.ProjectRequestDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ID")
    private Long id;
    private String title;
    private String content;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Embedded
    private Introduce introduce;

    @OneToMany
    private List<Account> applicant = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    private Account account;

    protected Project(Account account, String title, String content, LocalDateTime startTime, LocalDateTime endTime, Introduce introduce) {
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime.plusDays(1).minusMinutes(1);
        this.introduce = introduce;
        this.account = account;
    }


    protected Project() {

    }

    public static Project of(Account account, ProjectRequestDto projectRequestDto) {
        return new Project(
                account,
                projectRequestDto.getTitle(),
                projectRequestDto.getContent(),
                projectRequestDto.getStartTime(),
                projectRequestDto.getEndTime(),
                Introduce.of(projectRequestDto.getIntroduceRequestDto())
        );
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Introduce getIntroduce() {
        return introduce;
    }

    public List<Account> getApplicant() {
        return applicant;
    }

    public Account getAccount() {
        return account;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
