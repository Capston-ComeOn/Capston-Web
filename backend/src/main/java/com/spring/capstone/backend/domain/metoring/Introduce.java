package com.spring.capstone.backend.domain.metoring;

import com.spring.capstone.backend.service.dto.IntroduceDto;

import javax.persistence.Embeddable;

@Embeddable
public class Introduce {

    private String mento;
    private String target;
    private String metting;
    private String etc;

    public static Introduce of(IntroduceDto introduceDto) {
        return new Introduce(
                introduceDto.getMento(),
                introduceDto.getTarget(),
                introduceDto.getMetting(),
                introduceDto.getEtc()
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
