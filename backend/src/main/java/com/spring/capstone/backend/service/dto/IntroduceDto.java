package com.spring.capstone.backend.service.dto;

public class IntroduceDto {

    private String mento;
    private String target;
    private String metting;
    private String etc;

    public IntroduceDto(String mento, String target, String metting, String etc) {
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
