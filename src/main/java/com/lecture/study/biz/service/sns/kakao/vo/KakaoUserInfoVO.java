package com.lecture.study.biz.service.sns.kakao.vo;

import lombok.Data;

@Data
public class KakaoUserInfoVO {

    // 카카오 고유 ID
    private String id;

    // 유저 명
    private String nickname;
}
