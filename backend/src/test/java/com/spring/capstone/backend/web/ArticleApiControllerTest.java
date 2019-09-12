package com.spring.capstone.backend.web;

import com.spring.capstone.backend.domain.article.ArticleContents;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

class ArticleApiControllerTest extends AbstractControllerTest{
    private String cookie;

    @Test
    void 게시글_조회() {
        webTestClient.get().uri("/api/articles/{articles}",1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$..title").isEqualTo("title")
                .jsonPath("$..contents").isEqualTo("contents");
    }

    @Test
    void 게시글_생성_페이지_접근() {
        webTestClient.get().uri("/api/articles/new")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 게시글_생성() {
        ArticleContents articleContents = new ArticleContents("new title", "new contents");
        webTestClient.post().uri("/api/articles/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(articleContents), ArticleContents.class)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 게시글_수정() {
        ArticleContents articleContents = new ArticleContents("update title", "update contents");

        webTestClient.put().uri("/api/articles/{articleId}", 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(articleContents), ArticleContents.class)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk();

        webTestClient.get().uri("/api/articles/{articles}",1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$..title").isEqualTo("update title")
                .jsonPath("$..contents").isEqualTo("update contents");

    }


    @Test
    void 게시글_삭제() {
        webTestClient.post().uri("/api/articles/{articleId}",2)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk();

        webTestClient.get().uri("/api/articles/{articleId}",2)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.message").isNotEmpty();
    }

}
