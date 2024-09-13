package com.lecture.study.biz.service.comment.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("StudyCommentSaveReqVO")
public class StudyCommentSaveReqVO {

    // 스터디 댓글 ID
    private String stdyCommentId;

    // 스터디 ID
    private String stdyId;

    // 스터티 댓글 내용
    private String stdyCommentCon;

    // 스터디 부모 댓글 ID
    private String stdyParentCommentId;

    // 등록 사용자
    private String rgsnUserId;

    // 수정 사용자
    private String amnnUserId;
}
