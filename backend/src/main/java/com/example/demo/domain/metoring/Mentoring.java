package com.example.demo.domain.metoring;

import com.example.demo.domain.accounts.Account;
import com.example.demo.service.dto.MentoringRequestDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "mentoring")
public class Mentoring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENTORING_ID")
    private Long id;
    private String title;
    private String content;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Embedded
    private Introduce introduce;

    @OneToMany
    private List<Account> mentees = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    private Account mento;

    public static Mentoring of(Account mento, MentoringRequestDto mentoringRequestDto) {
        return new Mentoring(
                mento,
                mentoringRequestDto.getTitle(),
                mentoringRequestDto.getContent(),
                mentoringRequestDto.getStartTime(),
                mentoringRequestDto.getEndTime(),
                Introduce.of(mentoringRequestDto.getIntroduceRequestDto())
        );
    }


    protected Mentoring(Account mento, String title, String content, LocalDateTime startTime, LocalDateTime endTime, Introduce introduce) {
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime.plusDays(1).minusMinutes(1);
        this.introduce = introduce;
        this.mento = mento;
    }


    protected Mentoring() {

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

    public List<Account> getMentees() {
        return mentees;
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

    public Account getMento() {
        return mento;
    }
}
