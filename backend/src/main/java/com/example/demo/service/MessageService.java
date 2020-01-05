package com.example.demo.service;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.AccountRepository;
import com.example.demo.domain.message.Message;
import com.example.demo.domain.message.MessageRepository;
import com.example.demo.service.dto.MessageRequestDto;
import com.example.demo.service.dto.MessageResponseDto;
import com.example.demo.service.exception.NotFoundDataException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageService {

    private AccountRepository accountRepository;
    private MessageRepository messageRepository;

    public MessageService(AccountRepository accountRepository, MessageRepository messageRepository) {
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void save(String email, Long fromId, MessageRequestDto m) {
        Account from = accountRepository.findById(fromId).orElseThrow(NotFoundDataException::new);
        Account to = accountRepository.findByEmail(email).orElseThrow(NotFoundDataException::new);
        messageRepository.save(Message.of(to, from, m.getContent()));
    }

    public List<MessageResponseDto> getMessage(Account account, long fromId) {
        Account from = accountRepository.findById(fromId).orElseThrow(NotFoundDataException::new);
        Account to = accountRepository.findByEmail(account.getEmail()).orElseThrow(NotFoundDataException::new);

        List<MessageResponseDto> messages = messageRepository.findByToAndFrom(to.getId(), from.getId());
        return messages;
    }

    public List<MessageResponseDto> getRecentContactList(Account account) {
        List<Message> messages = messageRepository.findByRecentContacts(account.getId());
        List<MessageResponseDto> list = new ArrayList<>();

        //TODO 쿼리로 끝낼 것
        HashMap<Long, Long> map = new HashMap<>();
        for (Message m : messages) {

            Account to = m.getTo();
            Account from = m.getFrom();

            Account key = account.getId() == to.getId() ? from : to;

            if (!map.containsKey(key.getId())) {
                map.put(key.getId(), key.getId());
                list.add(MessageResponseDto.of(m));
            }
        }

        return list;
    }
}


