package com.example.demo.service.validator;

import com.example.demo.service.dto.MentoringRequestDto;
import com.example.demo.service.dto.ProjectRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class DateValidator {

    public void validator(ProjectRequestDto project, Errors errors) {
        LocalDateTime startTime = project.getStartTime();
        LocalDateTime endTime = project.getEndTime();
        checkTime(errors, startTime, endTime);
    }

    public void validator(MentoringRequestDto metoring, Errors errors) {
        LocalDateTime startTime = metoring.getStartTime();
        LocalDateTime endTime = metoring.getEndTime();
        checkTime(errors, startTime, endTime);
    }

    private void checkTime(Errors errors, LocalDateTime startTime, LocalDateTime endTime) {
        if (endTime.isBefore(LocalDateTime.now())) {
            errors.rejectValue("endTime", "현재시간보다 느립니다.");
        }

        if (endTime.isBefore(startTime)) {
            errors.rejectValue("startTime", "시작시간이 끝나는 시간보다 빠릅니다.");
        }
    }

}
