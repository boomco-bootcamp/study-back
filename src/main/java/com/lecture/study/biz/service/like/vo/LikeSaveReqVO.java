package com.lecture.study.biz.service.like.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("LikeSaveReqVO")
public class LikeSaveReqVO {

    // 스터디 ID
    private String stdyId;

    // 등록 사용자
    private String rgsnUserId;

    // 수정 사용자
    private String amnnUserId;
}
