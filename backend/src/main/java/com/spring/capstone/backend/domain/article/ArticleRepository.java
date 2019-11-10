package com.spring.capstone.backend.domain.article;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Article> findByCategoryId(Long categoryId, Pageable pageable) {
        return em.createQuery("select a from Article a join fetch a.author join fetch a.category c where c.id=:id order by a.id DESC", Article.class)
                .setParameter("id", categoryId)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    public Article findByCategoryIdAndArticleId(Long categoryId, Long articleId) {
        return  em.createQuery("select a from Article a join fetch a.category c join fetch a.author where c.id=:categoryId and a.id=:articleId",Article.class)
                .setParameter("categoryId", categoryId)
                .setParameter("articleId", articleId)
                .getSingleResult();
    }

    public Long countByCategoryId(Long categoryId) {
        // TODO 페치 조인시 오류

        return em.createQuery("select count(a.id) from Article a join a.category c where c.id=:categoryId", Long.class)
                .setParameter("categoryId", categoryId)
                .getSingleResult();
    }

    public Article findById(long id) {
        return em.find(Article.class, id);
    }

    public long save(Article article) {
        em.persist(article);
        return article.getId();
    }
}
