package com.spring.capstone.backend.service.dto;

import com.spring.capstone.backend.domain.metoring.Mentoring;

public class MentoringResponseDto {

    private Long id;
    private String title;
    private IntroduceResponseDto introduce;
    private Boolean use;
    private AccountResponseDto mento;

    protected MentoringResponseDto(Long id, String title, IntroduceResponseDto introduce, Boolean use, AccountResponseDto mento) {
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.use = use;
        this.mento = mento;
    }

    public static MentoringResponseDto withMentoring(Mentoring mentoring){
        return new MentoringResponseDto(
                mentoring.getId(),
                mentoring.getTitle(),
                IntroduceResponseDto.of(mentoring.getIntroduce()),
                mentoring.getUse(),
                AccountResponseDto.withAccount(mentoring.getAccount())
        );
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
        return use;
    }

    public AccountResponseDto getMento() {
        return mento;
    }
}
