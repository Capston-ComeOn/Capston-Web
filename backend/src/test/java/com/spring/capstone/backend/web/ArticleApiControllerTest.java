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
                .header("Cookie", extractJSessionId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 게시글_생성() {
        ArticleContents articleContents = new ArticleContents("new title", "new contents");
        webTestClient.post().uri("/api/articles/new")
                .header("Cookie", extractJSessionId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(articleContents), ArticleContents.class)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk();
    }

}
