package com.spring.capstone.backend.domain.metoring;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.service.dto.MentoringRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<Account> mentees = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    private Account mento;

    public static Mentoring of(Account mento, MentoringRequestDto mentoringRequestDto) {
        return new Mentoring(
                mento,
                mentoringRequestDto.getTitle(),
                Introduce.of(mentoringRequestDto.getIntroduceRequestDto())
        );
    }


    protected Mentoring(Account mento, String title, Introduce introduce) {
        this.title = title;
        this.introduce = introduce;
        this.mento = mento;
        this.use = true;
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
