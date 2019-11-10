package com.spring.capstone.backend.service.dto;

import com.spring.capstone.backend.domain.metoring.Mentoring;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MentoringResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTIme;
    private IntroduceResponseDto introduce;
    private AccountResponseDto mento;

    public MentoringResponseDto(Long id, String title, String content, LocalDateTime startTime, LocalDateTime endTIme, IntroduceResponseDto introduce, AccountResponseDto mento) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.endTIme = endTIme;
        this.introduce = introduce;
        this.mento = mento;
    }

    public static MentoringResponseDto withMentoring(Mentoring mentoring) {
        return new MentoringResponseDto(
                mentoring.getId(),
                mentoring.getTitle(),
                mentoring.getContent(),
                mentoring.getStartTime(),
                mentoring.getEndTime(),
                IntroduceResponseDto.of(mentoring.getIntroduce()),
                AccountResponseDto.withAccount(mentoring.getMento())
        );
    }

    public String getStartTime() {
        return startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getEndTime() {
        return endTIme.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
        if (LocalDateTime.now().isBefore(this.endTIme)) {
            return false;
        }
        return true;
    }

    public AccountResponseDto getMento() {
        return mento;
    }
}
