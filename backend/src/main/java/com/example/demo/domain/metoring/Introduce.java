package com.example.demo.domain.metoring;

import com.example.demo.service.dto.IntroduceRequestDto;

import javax.persistence.Embeddable;

@Embeddable
public class Introduce {

    private String mento;
    private String target;
    private String metting;
    private String etc;

    protected Introduce() {

    }

    public static Introduce of(IntroduceRequestDto introduceRequestDto) {
        return new Introduce(
                introduceRequestDto.getMento(),
                introduceRequestDto.getTarget(),
                introduceRequestDto.getMetting(),
                introduceRequestDto.getEtc()
        );
    }

    protected Introduce(String mento, String target, String metting, String etc) {
        this.mento = mento;
        this.target = target;
        this.metting = metting;
        this.etc = etc;
    }

    public String getMento() {
        return mento;
    }

    public void setMento(String mento) {
        this.mento = mento;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMetting() {
        return metting;
    }

    public void setMetting(String metting) {
        this.metting = metting;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}
