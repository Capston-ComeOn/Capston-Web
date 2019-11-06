package com.spring.capstone.backend.web.controller;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.CurrentAccount;
import com.spring.capstone.backend.domain.message.Message;
import com.spring.capstone.backend.domain.message.MessageResponse;
import com.spring.capstone.backend.service.MessageService;
import com.spring.capstone.backend.service.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageApiController {

    private MessageService messageService;

    public MessageApiController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{from}")
    public ResponseEntity getMessage(@CurrentAccount Account account, @PathVariable Long from) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        List<Message> message;
        try {
            message = messageService.getMessage(account, from);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createMessage(@CurrentAccount Account account, @RequestBody MessageDto messageDto) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        try {
            messageService.save(account, messageDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getRecentContactList(@CurrentAccount Account account) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        List<MessageResponse> recentContactList;
        try {
            recentContactList = messageService.getRecentContactList(account);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(recentContactList, HttpStatus.OK);
    }
}
