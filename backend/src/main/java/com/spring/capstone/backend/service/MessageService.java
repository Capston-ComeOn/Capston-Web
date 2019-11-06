package com.spring.capstone.backend.service;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.domain.message.Message;
import com.spring.capstone.backend.domain.message.MessageRepository;
import com.spring.capstone.backend.domain.message.MessageResponse;
import com.spring.capstone.backend.service.dto.MessageDto;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
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
    public void save(Account account, MessageDto messageDto) {
        Account from = accountRepository.findById(messageDto.getFrom()).orElseThrow(NotFoundDataException::new);
        Account to = accountRepository.findByEmail(account.getEmail()).orElseThrow(NotFoundDataException::new);
        Message message = Message.of(to, from, messageDto.getContent());
        messageRepository.save(message);
    }

    public List<Message> getMessage(Account account, long fromId) {
        Account from = accountRepository.findById(fromId).orElseThrow(NotFoundDataException::new);
        Account to = accountRepository.findByEmail(account.getEmail()).orElseThrow(NotFoundDataException::new);

        // TODO DB 에서 조회 해서 정렬해서 가져오기
        List<Message> messages = messageRepository.findByToAndFrom(to, from);
        messages.addAll(messageRepository.findByToAndFrom(from, to));
        messages.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getCreated().compareTo(o2.getCreated());
            }
        });
        return messages;
    }

    public List<MessageResponse> getRecentContactList(Account account) {

        // TODO DB 쿼리 수정 하기
        // 해당 유저의 모든 메세지 대화 기록을 시간(내림차순) 으로 정렬
        List<Message> messages = messageRepository.findByToOrFrom(account);

        List<MessageResponse> list = new ArrayList<>();
        HashMap<Long, Long> map = new HashMap<>();

        for (Message message : messages) {

            Account to = message.getTo();
            Account from = message.getFrom();

            Account key = account.getId() == to.getId() ? from : to;

            if (!map.containsKey(key.getId())) {
                map.put(key.getId(), key.getId());
                MessageResponse messageResponse = new MessageResponse(
                        message.getId(),
                        key,
                        message.getContent(),
                        message.getCreated()
                );
                list.add(messageResponse);
            }
        }

        return list;
    }
}
