package com.spring.capstone.backend.domain.message;

import com.spring.capstone.backend.domain.accounts.Account;
import org.springframework.cglib.core.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageResponse {
    private Long id;

    private Account account;
    private String content;

    private LocalDateTime created;


    public MessageResponse(Long id, Account account, String content, LocalDateTime created) {
        this.id = id;
        this.account = account;
        this.content = content;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public String getContent() {
        return content;
    }

    public String getCreated() {
        return created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
