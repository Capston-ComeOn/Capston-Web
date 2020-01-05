package com.example.demo.service.dto;


import com.example.demo.domain.metoring.Introduce;

public class IntroduceResponseDto {

    private String mento;
    private String target;
    private String metting;
    private String etc;

    public IntroduceResponseDto(String mento, String target, String metting, String etc) {
        this.mento = mento;
        this.target = target;
        this.metting = metting;
        this.etc = etc;
    }

    public static IntroduceResponseDto of(Introduce introduce) {
        return new IntroduceResponseDto(
                introduce.getMento(),
                introduce.getTarget(),
                introduce.getMetting(),
                introduce.getEtc()
        );
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
