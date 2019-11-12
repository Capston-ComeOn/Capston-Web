package com.example.demo.domain.message;

import com.example.demo.service.dto.MessageResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MessageRepository {

    @PersistenceContext
    private EntityManager em;

    public List<MessageResponseDto> findByToAndFrom(Long to, Long from) {
        String jpql = "select m from Message m join fetch m.to t join fetch m.from f where (t.id=:to AND f.id=:from) OR (t.id=:from AND f.id=:to) order by m.created";
        return em.createQuery(jpql, Message.class)
                .setParameter("to", to)
                .setParameter("from", from)
                .getResultList().stream().map(MessageResponseDto::of).collect(Collectors.toList());
    }

    public List<Message> findByRecentContacts(Long id) {
        // TODO 최적화
        String jpql = "select m from Message m join fetch m.to t join fetch m.from f where (t.id=:id OR f.id=:id) order by m.created desc ";
        return em.createQuery(jpql, Message.class)
                .setParameter("id", id)
                .getResultList();
    }

    public Long save(Message message) {
        em.persist(message);
        return message.getId();
    }
}
