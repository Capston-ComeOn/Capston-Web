package com.spring.capstone.backend.domain.message;


public class MessageVO {

    private long from;
    private String content;

    public MessageVO(long from, String content) {
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

