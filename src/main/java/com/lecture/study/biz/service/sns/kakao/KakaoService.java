package com.lecture.study.biz.service.sns.kakao;

import com.lecture.study.biz.service.sns.kakao.vo.KakaoUserInfoVO;

import java.util.Map;

public interface KakaoService {

    /**
     * 카카오 로그인 accessToken 요청
     * @param code
     * @return
     * @throws Exception
     */
    String getAccessToken(String code) throws Exception;

    /**
     * 카카오 로그인 유저 정보 요청
     * @param accessToken
     * @return
     * @throws Exception
     */
    KakaoUserInfoVO getUserInfo(String accessToken) throws Exception;

    /**
     * 카카오 로그아웃
     * @param accessToken
     * @throws Exception
     */
    void logout(String accessToken) throws Exception;
}
