package com.spring.capstone.backend.domain.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByCategoryId(Long categoryId, Pageable pageable);

    Optional<Article> findByCategoryIdAndId(Long categoryId, Long id);

    int countByCategoryId(Long categoryId);
}
