package com.spring.capstone.backend.web.controller;

import com.spring.capstone.backend.domain.accounts.Account;
import com.spring.capstone.backend.domain.accounts.CurrentAccount;
import com.spring.capstone.backend.domain.metoring.Mentoring;
import com.spring.capstone.backend.service.MentoringService;
import com.spring.capstone.backend.service.dto.MentoringRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/mentoring")
public class MentoringApiController {

    private MentoringService mentoringService;

    public MentoringApiController(MentoringService mentoringService) {
        this.mentoringService = mentoringService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getMentoring(@CurrentAccount Account account, @PathVariable Long id) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        Mentoring mentoring;
        try {
            mentoring = mentoringService.getMentoring(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(mentoring, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMentoringList(@CurrentAccount Account account) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        List<Mentoring> mentoringList;
        try {
            mentoringList = mentoringService.getMentoringList();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(mentoringList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createMentoring(@CurrentAccount Account account, @RequestBody MentoringRequestDto mentoringRequestDto) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        try {
            mentoringService.save(account.getEmail(), mentoringRequestDto);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
