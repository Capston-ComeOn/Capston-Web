package com.example.demo.service.dto;

public class MessageRequestDto {

    private String content;

    public MessageRequestDto() {
    }

    public MessageRequestDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
