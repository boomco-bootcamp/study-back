package com.lecture.study.biz.service.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("MemberResVO")
public class MemberResVO {

    // 스터디 참여인원 ID
    private String stdyMemberId;

    // 스터디 ID
    private String stdyId;

    // 유저 ID
    private String userId;

    // 유저 명
    private String userNm;

    // 스터디 참여 상태
    private String stdyMemberYn;

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
