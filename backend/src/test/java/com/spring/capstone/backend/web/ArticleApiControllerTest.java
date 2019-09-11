package com.spring.capstone.backend.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArticleApiControllerTest extends AbstractControllerTest{
    private String cookie;

    @BeforeEach
    void setUp(){
        cookie = extractJSessionId();
    }

    @Test
    public void 게시글_조회() {
        webTestClient.get().uri("/api/articles/{articles}",1)
                .header("Cookie", cookie)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("title")
                .jsonPath("$.contents").isEqualTo("contetns");
    }
}
