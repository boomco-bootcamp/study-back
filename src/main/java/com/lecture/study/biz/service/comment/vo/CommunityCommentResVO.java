package com.lecture.study.biz.service.comment.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("CommunityCommentResVO")
public class CommunityCommentResVO {

    // 스터디 커뮤니티 댓글 ID
    private String stdyComCommentId;

    // 스터디 커뮤니티 ID
    private String stdyComId;

    // 스터디 커뮤니티 댓글 내용
    private String stdyComCommentCon;

    // 스터디 부모 커뮤니티 댓글 ID
    private String stdyParentComCommentId;

    // 커뮤니티 자식 댓글 목록
    private List<CommunityCommentResVO> stdyChildComCommentList;

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
}
