package com.spring.capstone.backend.domain.metoring;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.service.dto.MentoringDto;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Mentoring {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @Embedded
    private Introduce introduce;
    private Boolean use;

    @OneToMany
    private List<Account> mentees;

    @ManyToOne(fetch = LAZY)
    private Account mento;

    public static Mentoring of(Account mento, MentoringDto mentoringDto) {
        return new Mentoring(
                mento,
                mentoringDto.getTitle(),
                Introduce.of(mentoringDto.getIntroduceDto())
        );
    }

    public Mentoring(Account mento, String title, Introduce introduce) {
        this.title = title;
        this.introduce = introduce;
        this.mento = mento;
    }

    private Mentoring(String title, Introduce introduce, Boolean use, List<Account> mentees, Account mento) {
        this.title = title;
        this.introduce = introduce;
        this.use = true;
        this.mentees = mentees;
        this.mento = mento;
    }

    private Mentoring() {

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

    public Boolean getUse() {
        return use;
    }

    public List<Account> getMentees() {
        return mentees;
    }

    public Account getAccount() {
        return mento;
    }
}
