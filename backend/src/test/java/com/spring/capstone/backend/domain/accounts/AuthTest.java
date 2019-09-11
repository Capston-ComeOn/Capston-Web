package com.spring.capstone.backend.domain.accounts;

import com.spring.capstone.backend.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void create_token() throws Exception {

        Account account=new Account();
        account.setName("kimdonggyu");
        String email = "donggyu@email.com";
        account.setEmail(email);
        String password = "1234";
        account.setPassword(password);
        account.setRoles(new HashSet<>(Arrays.asList(AccountRoles.ADMIN,AccountRoles.USER)));

        Account savedAccount = accountService.accountSave(account);

        String clientId = "id";
        String clientSecret= "secret";

        this.mockMvc.perform(post( "/oauth/token")
                .with(httpBasic(clientId, clientSecret))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("username", email)
                .param("password", password)
                .param("grant_type", "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists());


    }
}
