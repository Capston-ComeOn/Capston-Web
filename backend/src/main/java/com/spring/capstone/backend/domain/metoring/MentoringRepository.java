package com.spring.capstone.backend.domain.metoring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentoringRepository extends JpaRepository<Mentoring,Long> {
}
