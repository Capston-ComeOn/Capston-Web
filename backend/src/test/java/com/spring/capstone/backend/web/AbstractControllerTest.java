package com.spring.capstone.backend.web;

import com.spring.capstone.backend.service.dto.AuthenticationAccountDto;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.stream.Stream;

import static org.springframework.web.reactive.function.BodyInserters.fromFormData;

@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractControllerTest {

    @Autowired
    WebTestClient webTestClient;

//    EntityExchangeResult<byte[]> login(AuthenticationAccountDto authenticationAccountDto) {
//        return webTestClient.post().uri("/login")
//                .body(fromFormData("email", authenticationAccountDto.getEmail())
//                        .with("password", authenticationAccountDto.getPassword()))
//                .exchange()
//                .expectStatus().is3xxRedirection()
//                .expectHeader().valueMatches("Location", ".*/.*")
//                .expectBody()
//                .returnResult();
//    }
//
//    String extractJSessionId() {
//        EntityExchangeResult<byte[]> loginResult =
//                login(new AuthenticationAccountDto("user@gmail.com", "123"));
//
//        String[] cookies = loginResult.getResponseHeaders().get("set-Cookie").stream()
//                .filter(it -> it.contains("JSESSIONID"))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("jSessionId가 없습니다."))
//                .split(";");
//        return Stream.of(cookies)
//                .filter(it -> it.contains("JSESSIONID"))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("jSessioId가 없습니다."))
//                .split("=")[1];
//    }
}
