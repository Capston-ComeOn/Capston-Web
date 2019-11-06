package com.spring.capstone.backend.service.dto;

import com.spring.capstone.backend.domain.metoring.Introduce;

public class MentoringDto {

    private String title;
    private IntroduceDto introduceDto;

    public MentoringDto(String title, IntroduceDto introduceDto) {
        this.title = title;
        this.introduceDto = introduceDto;
    }

    public String getTitle() {
        return title;
    }

    public IntroduceDto getIntroduceDto() {
        return introduceDto;
    }
}

