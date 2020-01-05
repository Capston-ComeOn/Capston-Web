package com.example.demo.web.controller;

import com.example.demo.domain.accounts.Account;
import com.example.demo.domain.accounts.CurrentAccount;
import com.example.demo.service.ProjectService;
import com.example.demo.service.dto.ProjectRequestDto;
import com.example.demo.service.dto.ProjectResponseDto;
import com.example.demo.service.validator.DateValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectApiController {

    private ProjectService projectService;
    private DateValidator dateValidator;

    public ProjectApiController(ProjectService projectService, DateValidator dateValidator) {
        this.projectService = projectService;
        this.dateValidator = dateValidator;
    }

    @GetMapping("/{id}")
    public ResponseEntity getProject(@CurrentAccount Account account, @PathVariable Long id) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        ProjectResponseDto project;
        try {
            project = projectService.getProject(id);

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(project, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getProjectList(@CurrentAccount Account account) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        List<ProjectResponseDto> projectList;
        try {
            projectList = projectService.getProjectList();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(projectList, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity apply(@CurrentAccount Account account, @PathVariable Long id) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        try {
            projectService.apply(account.getEmail(), id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createProject(@CurrentAccount Account account, @RequestBody @Valid ProjectRequestDto projectRequestDto, Errors errors) {
        if (account == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        dateValidator.validator(projectRequestDto, errors);

        if (errors.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try {
            projectService.save(account.getEmail(), projectRequestDto);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
