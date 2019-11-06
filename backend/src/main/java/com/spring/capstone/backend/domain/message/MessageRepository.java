package com.spring.capstone.backend.domain.message;

import com.spring.capstone.backend.service.dto.MessageResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MessageRepository {

    @PersistenceContext
    private EntityManager em;

    public List<MessageResponseDto> findByToAndFrom(Long toId, Long fromId) {
        String jpql = "select m from Message m join fetch m.to t join fetch m.from f where (t.id=:toId AND f.id=:fromId) OR (t.id=:fromId AND f.id=:toId) order by m.created";
        return em.createQuery(jpql, Message.class)
                .setParameter("toId", toId)
                .setParameter("fromId", fromId)
                .getResultList().stream().map(MessageResponseDto::of).collect(Collectors.toList());
    }

    public List<MessageResponseDto> findByRecentContacts(Long id) {
        String jpql = "select m from Message m join fetch m.to t join fetch m.from f where (t.id=:id OR f.id=:id) order by m.created desc ";
        return em.createQuery(jpql, Message.class)
                .setParameter("id", id)
                .getResultList().stream().map(MessageResponseDto::of).collect(Collectors.toList());
    }

    public Long save(Message message) {
        em.persist(message);
        return message.getId();
    }
}
