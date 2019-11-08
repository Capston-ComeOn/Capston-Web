package com.spring.capstone.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.oauth2.common.util.Jackson2JsonParser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
class ArticleApiControllerTest extends A {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void 게시글_조회() {
//        webTestClient.get().uri("/api/articles/{articles}",1)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$..title").isEqualTo("title")
//                .jsonPath("$..contents").isEqualTo("contents");
//    }

//    @Test
//    void 게시글_생성_페이지_접근() {
//        webTestClient.get().uri("/api/articles")
//                .exchange()
//                .expectStatus().isOk();
//    }

    @org.junit.jupiter.api.Test
    void create() throws Exception {

//        ArticleVO articleVO = new ArticleVO("title", "contents");
//        webTestClient.post().uri("/api/article")
//                .header(HttpHeaders.AUTHORIZATION, getBearerToken())
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .body(Mono.just(articleVO), ArticleVO.class)
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .exchange()
//                .expectStatus().isCreated();
    }

    private String getAccessToken() throws Exception {
        ResultActions perform = this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic("id", "secret"))
                .param("username", "user@email.com")
                .param("password", "123")
                .param("grant_type", "password"));

        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        Jackson2JsonParser parser = new Jackson2JsonParser();
        return parser.parseMap(contentAsString).get("access_token").toString();
    }

    private String getBearerToken() throws Exception {
        return "Bearer " + getAccessToken();
    }


//    @Test
//    void 게시글_생성() {
//        ArticleContents articleContents = new ArticleContents("new title", "new contents");
//        webTestClient.post().uri("/api/articles")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .body(Mono.just(articleContents), ArticleContents.class)
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .exchange()
//                .expectStatus().isOk();
//    }

//    @Test
//    void 게시글_수정() {
//        ArticleContents articleContents = new ArticleContents("update title", "update contents");
//
//        webTestClient.put().uri("/api/articles/{articleId}", 1)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .body(Mono.just(articleContents), ArticleContents.class)
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .exchange()
//                .expectStatus().isOk();
//
//        webTestClient.get().uri("/api/articles/{articles}",1)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$..title").isEqualTo("update title")
//                .jsonPath("$..contents").isEqualTo("update contents");
//
//    }


//    @Test
//    void 게시글_삭제() {
//        webTestClient.post().uri("/api/articles/{articleId}",2)
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .exchange()
//                .expectStatus().isOk();
//
//        webTestClient.get().uri("/api/articles/{articleId}",2)
//                .exchange()
//                .expectStatus().isBadRequest()
//                .expectBody()
//                .jsonPath("$.message").isNotEmpty();
//    }

}
