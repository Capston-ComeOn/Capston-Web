package com.spring.capstone.backend.domain.message;

import com.spring.capstone.backend.domain.accounts.Account;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TO_ID")
    private Account to;

    @ManyToOne
    @JoinColumn(name = "FROM_ID")
    private Account from;

    @NotEmpty
    private String content;

    private LocalDateTime created;

    public Message() {

    }

    public static Message of(Account to, Account from, String content) {
        return new Message(to, from, content);
    }

    public Message(Account to, Account from, @NotEmpty String content) {
        this.to = to;
        this.from = from;
        this.content = content;
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Account getTo() {
        return to;
    }

    public Account getFrom() {
        return from;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

}
