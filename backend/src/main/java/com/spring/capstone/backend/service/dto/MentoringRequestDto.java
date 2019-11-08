package com.spring.capstone.backend.service.dto;

public class MentoringRequestDto {

    private String title;
    private IntroduceRequestDto introduceRequestDto;

    public MentoringRequestDto(String title, IntroduceRequestDto introduceRequestDto) {
        this.title = title;
        this.introduceRequestDto = introduceRequestDto;
    }

    public String getTitle() {
        return title;
    }

    public IntroduceRequestDto getIntroduceRequestDto() {
        return introduceRequestDto;
    }
}

