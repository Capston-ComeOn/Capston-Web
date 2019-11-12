package com.example.demo.web.controller;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.CurrentAccount;
import com.example.demo.service.MentoringService;
import com.example.demo.service.dto.MentoringRequestDto;
import com.example.demo.service.dto.MentoringResponseDto;
import com.example.demo.service.validator.DateValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/mentoring")
public class MentoringApiController {

    private MentoringService mentoringService;
    private DateValidator dateValidator;

    public MentoringApiController(MentoringService mentoringService, DateValidator dateValidator) {
        this.mentoringService = mentoringService;
        this.dateValidator = dateValidator;
    }

    @GetMapping("/{id}")
    public ResponseEntity getMentoring(@CurrentAccount Account account, @PathVariable Long id) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        MentoringResponseDto mentoring;
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
        List<MentoringResponseDto> mentoringList;
        try {
            mentoringList = mentoringService.getMentoringList();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(mentoringList, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity addMentee(@CurrentAccount Account account, @PathVariable Long id) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        try {
            mentoringService.addMentee(account.getEmail(), id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createMentoring(@CurrentAccount Account account, @RequestBody @Valid MentoringRequestDto mentoringRequestDto, Errors errors) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        dateValidator.validator(mentoringRequestDto, errors);

        if (errors.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try {
            mentoringService.save(account.getEmail(), mentoringRequestDto);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
