package com.spring.capstone.backend.service.dto;

import javax.validation.constraints.NotEmpty;

public class IntroduceRequestDto {

    @NotEmpty
    private String mento;
    @NotEmpty
    private String target;
    @NotEmpty
    private String metting;
    @NotEmpty
    private String etc;

    public IntroduceRequestDto(String mento, String target, String metting, String etc) {
        this.mento = mento;
        this.target = target;
        this.metting = metting;
        this.etc = etc;
    }

    public String getMento() {
        return mento;
    }

    public String getTarget() {
        return target;
    }

    public String getMetting() {
        return metting;
    }

    public String getEtc() {
        return etc;
    }
}
