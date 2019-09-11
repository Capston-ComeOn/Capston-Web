package com.spring.capstone.backend.web;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleApiControllerTest extends AbstractControllerTest{

    private String cookie;
    private WebTestClient webTestClient;

    public ArticleApiControllerTest(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @BeforeEach
    public void setUp() {
        cookie = extractJSessionId();
    }

    @Test
    public void 게시글_조회() {
        System.out.println("웹: "+ webTestClient);
        webTestClient.get().uri("/api/articles/{articleId}",1)
                .cookie("set-Cookie", cookie)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("타이틀")
                .jsonPath("$.contents").isEqualTo("컨텐츠");
    }
}
