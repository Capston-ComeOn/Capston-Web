package com.spring.capstone.backend.domain.message;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.AccountRepository;
import com.spring.capstone.backend.service.AccountService;
import com.spring.capstone.backend.service.exception.NotFoundDataException;
import org.aspectj.weaver.ast.Not;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
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
    public void save(Account account, MessageVO messageVO) {
        Account from = accountRepository.findById(messageVO.getFrom()).orElseThrow(NotFoundDataException::new);
        Account to = accountRepository.findByEmail(account.getEmail()).orElseThrow(NotFoundDataException::new);
        Message message = Message.of(to, from, messageVO.getContent());
        messageRepository.save(message);
    }

    public List<Message> getMessage(Account account, long fromId) {
        Account from = accountRepository.findById(fromId).orElseThrow(NotFoundDataException::new);
        Account to = accountRepository.findByEmail(account.getEmail()).orElseThrow(NotFoundDataException::new);
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
}
