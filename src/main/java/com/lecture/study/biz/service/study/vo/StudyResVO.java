package com.lecture.study.biz.service.study.vo;

import com.lecture.study.biz.service.member.vo.MemberResVO;
import com.lecture.study.biz.service.tag.vo.TagVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.util.List;

/**
 * 스터디 조회 관련 Res VO
 */
@Data
@Alias("StudyResVO")
public class StudyResVO {

    // 스터디 ID
    private String stdyId;

    // 이전 스터디 ID
    private String previousStdyId;
    // 다음 스터디 ID
    private String nextStdyId;

    // 스터티 명
    private String stdyNm;

    // 스터디 내용
    private String stdyCon;

    // 스터디 시작일
    private String stdyStDt;

    // 스터디 종료일
    private String stdyEnDt;

    // 스터디 상태
    private String stdySt;

    // 스터디 카테고리 ID
    private String stdyCatId;
    // 스터디 카테고리 명
    private String stdyCatNm;

    // 삭제 여부
    private String delYn;

    // 등록 사용자
    private String rgsnUserId;
    // 등록 사용자 명
    private String rgsnUserNm;

    // 등록 일시
    private Timestamp rgsnTs;

    // 수정 사용자
    private String amnnUserId;
    // 수정 사용자 명
    private String amnnUserNm;

    // 수정 일시
    private Timestamp amnnTs;

    // 태그 목록
    private List<TagVO> tagList;

    // 참여인원 목록
    private List<MemberResVO> memberList;

    // 총 방문자 수
    private int stdyViewsCnt;

    // 총 댓글 수
    private int stdyCommentCnt;

}