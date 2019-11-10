package com.spring.capstone.backend.service.dto;

import com.spring.capstone.backend.domain.metoring.Mentoring;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MentoringResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private IntroduceResponseDto introduce;
    private AccountResponseDto mento;
    private List<AccountResponseDto> mentees;

    public MentoringResponseDto(Long id, String title, String content, LocalDateTime startTime, LocalDateTime endTime, IntroduceResponseDto introduce, AccountResponseDto mento, List<AccountResponseDto> mentees) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.introduce = introduce;
        this.mento = mento;
        this.mentees = mentees;
    }

    public static MentoringResponseDto withMentoring(Mentoring mentoring) {
        return new MentoringResponseDto(
                mentoring.getId(),
                mentoring.getTitle(),
                mentoring.getContent(),
                mentoring.getStartTime(),
                mentoring.getEndTime(),
                IntroduceResponseDto.of(mentoring.getIntroduce()),
                AccountResponseDto.withAccount(mentoring.getMento()),
                mentoring.getMentees().stream().map(a -> AccountResponseDto.withAccount(a)).collect(Collectors.toList())
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

    public AccountResponseDto getMento() {
        return mento;
    }


    public List<AccountResponseDto> getMentees() {
        return mentees;
    }
}
