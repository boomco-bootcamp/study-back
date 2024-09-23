package com.lecture.study.biz.service.community.vo;

import com.lecture.study.biz.service.member.vo.MemberResVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.util.List;

/**
 * 스터디 커뮤니티 목록 조회 관련 Res VO
 */
@Data
@Alias("StudyComResVO")
public class StudyComResVO {

    // 스터디 커뮤니티 ID
    private String stdyComtId;

    // 스터디 ID
    private String stdyId;

    // 스터티 커뮤니티 제목
    private String stdyComTitle;

    // 스터디 커뮤니티 내용
    private String stdyComCon;

    // 스터디 커뮤니티 상태
    private String stdyComSt;

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

    // 스터디 커뮤니티 첨부파일 리스트
    private List<StudyComFileVO> fileList;

    // 스터디 참여 학생
    private List<MemberResVO> memberList;

    // 총 방문자 수
    private int studyComViewsCnt;

    // 총 댓글 수
    private int stdyComCommentCnt;
}
