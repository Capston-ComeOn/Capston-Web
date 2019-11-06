package com.spring.capstone.backend.domain.message;

import com.spring.capstone.backend.domain.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByToAndFrom(Account to, Account from);
    @Query("select m from Message m where m.to =:account Or m.from =:account ORDER BY m.created DESC")
    List<Message> findByToOrFrom(Account account);
}
