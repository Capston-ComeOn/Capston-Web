package com.spring.capstone.backend.service.dto;


public class MessageDto {

    private long from;
    private String content;

    public MessageDto() {
    }

    public MessageDto(long from, String content) {
        this.from = from;
        this.content = content;
    }

    public long getFrom() {
        return from;
    }

    public String getContent() {
        return content;
    }
}

