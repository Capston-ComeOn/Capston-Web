package com.example.demo.service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ProjectRequestDto {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    private IntroduceRequestDto introduceRequestDto;

    public ProjectRequestDto(String title, String content, LocalDateTime startTime, LocalDateTime endTime, IntroduceRequestDto introduceRequestDto) {
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.introduceRequestDto = introduceRequestDto;
    }

    public String getTitle() {
        return title;
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

    public IntroduceRequestDto getIntroduceRequestDto() {
        return introduceRequestDto;
    }
}