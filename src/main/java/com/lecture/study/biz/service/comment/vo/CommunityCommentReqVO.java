package com.lecture.study.biz.service.comment.vo;

import com.lecture.study.biz.service.comon.vo.PageVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("CommunityCommentReqVO")
public class CommunityCommentReqVO extends PageVO {

    // 스터디 커뮤니티 ID
    private String stdyComId;

    // 스터디 부모 커뮤니티 댓글 ID
    private String stdyParentComCommentId;
}
