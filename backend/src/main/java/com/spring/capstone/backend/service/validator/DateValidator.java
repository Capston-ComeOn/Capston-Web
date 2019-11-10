package com.spring.capstone.backend.service.validator;

import com.spring.capstone.backend.service.dto.MentoringRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class DateValidator {

    public void validator(MentoringRequestDto metoring, Errors errors) {
        LocalDateTime startTime = metoring.getStartTime();
        LocalDateTime endTime = metoring.getEndTime();

        if (endTime.isBefore(LocalDateTime.now())) {
            errors.rejectValue("endTime", "현재시간보다 느립니다.");
        }

        if (endTime.isBefore(startTime)) {
            errors.rejectValue("startTime", "시작시간이 끝나는 시간보다 빠릅니다.");
        }
    }

}
