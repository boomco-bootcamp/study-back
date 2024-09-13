package com.lecture.study.biz.service.comment.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("CommunityCommentSaveReqVO")
public class CommunityCommentSaveReqVO {

    // 스터디 커뮤니티 댓글 ID
    private String stdyComCommentId;

    // 스터디 커뮤니티 ID
    private String stdyComId;

    // 스터디 커뮤니티 댓글 내용
    private String stdyComCommentCon;

    // 스터디 부모 커뮤니티 댓글 ID
    private String stdyParentComCommentId;

    // 등록 사용자
    private String rgsnUserId;

    // 수정 사용자
    private String amnnUserId;
}
