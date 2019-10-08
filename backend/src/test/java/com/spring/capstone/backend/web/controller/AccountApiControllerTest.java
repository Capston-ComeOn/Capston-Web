package com.spring.capstone.backend.web.controller;

import com.spring.capstone.backend.domain.accounts.AccountVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountApiControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void 회원가입() {
        webTestClient.post().uri("/api/accounts/join")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(new AccountVO("user", "user@gmail.com" ,"Aa1234!")), AccountVO.class)
                .exchange()
                .expectStatus();
        // TODO: 2019-09-15 로그인 url로 이동하는 로직 필요함
//                .isCreated()
//                .expectHeader().valueMatches("location", ".*/login.*");
    }

}
