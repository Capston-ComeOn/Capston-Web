package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.domain.message.Message;
import com.spring.capstone.backend.domain.message.MessageRepository;
import com.spring.capstone.backend.service.dto.MessageRequestDto;
import com.spring.capstone.backend.service.dto.MessageResponseDto;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return messageRepository.findByRecentContacts(account.getId());
    }
}


//    List<MessageResponse> list = new ArrayList<>();
//    HashMap<Long, Long> map = new HashMap<>();
//
//        for (Message message : messages) {
//
//                Account to = message.getTo();
//                Account from = message.getFrom();
//
//                Account key = account.getId() == to.getId() ? from : to;
//
//                if (!map.containsKey(key.getId())) {
//                map.put(key.getId(), key.getId());
//                MessageResponse messageResponse = new MessageResponse(
//                message.getId(),
//                key,
//                message.getContent(),
//                message.getCreated()
//                );
//                list.add(messageResponse);
//                }
//                }
//
//                return list;