package com.lecture.study.biz.service.member.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("MemberSaveReqVO")
public class MemberSaveReqVO {

    // 스터디 참여인원 ID
    private String stdyMemberId;

    // 스터디 ID
    private String stdyId;

    // 유저 ID
    private String userId;

    // 스터디 참여 상태
    private String stdyMemberYn;

    // 등록 사용자
    private String rgsnUserId;

    // 수정 사용자
    private String amnnUserId;
}
