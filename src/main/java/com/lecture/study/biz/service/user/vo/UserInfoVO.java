package com.lecture.study.biz.service.user.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("UserInfoVO")
public class UserInfoVO {

    // 유저 ID
    private String userId;

    // 유저 패스워드
    private String userPswd;

    // 유저명
    private String userNm;

    // 유저 이메일 주소
    private String userEml;

    // 유저 전화번호
    private String userTel;

    // 유저 SNS_ID
    private String userSnsId;

    // 유저 SNS_TYPE
    private String userSnsType;

    // 시스템 관리자 여부
    private String sysAdmYn;

    // 삭제 여부
    private String delYn;

    // 등록 사용자
    private String rgsnUserId;

    // 등록 일시
    private Timestamp rgsnTs;

    // 수정 사용자
    private String amnnUserId;

    // 수정 일시
    private Timestamp amnnTs;


}
