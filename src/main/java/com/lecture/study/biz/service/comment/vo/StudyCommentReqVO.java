package com.lecture.study.biz.service.comment.vo;

import com.lecture.study.biz.service.comon.vo.PageVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("StudyCommentReqVO")
public class StudyCommentReqVO extends PageVO {

    // 스터디 ID
    private String stdyId;

    // 스터디 부모 댓글 ID
    private String stdyParentCommentId;
}
