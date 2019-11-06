package com.spring.capstone.backend.service.dto;

import com.spring.capstone.backend.domain.message.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageResponseDto {

    private Long id;
    private AccountResponseDto to;
    private AccountResponseDto from;
    private String content;
    private LocalDateTime created;

    protected MessageResponseDto() {
    }

    public static MessageResponseDto of(Message message) {
        return new MessageResponseDto(
                message.getId(),
                AccountResponseDto.withAccount(message.getTo()),
                AccountResponseDto.withAccount(message.getFrom()),
                message.getContent(),
                message.getCreated()
        );
    }

    private MessageResponseDto(Long id, AccountResponseDto to, AccountResponseDto from, String content, LocalDateTime created) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.content = content;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public AccountResponseDto getTo() {
        return to;
    }

    public AccountResponseDto getFrom() {
        return from;
    }

    public String getCreated() {
        return created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
