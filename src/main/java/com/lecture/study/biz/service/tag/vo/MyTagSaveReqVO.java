package com.lecture.study.biz.service.tag.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("MyTagSaveReqVO")
public class MyTagSaveReqVO {

    // 스터디 관심 태그 목록 ID
    private String stdyLikeTagId;

    // 유저 ID
    private String userId;

    // 관심 태그 내용
    private String stdyLikeTagCon;

    // 등록 사용자
    private String rgsnUserId;
}
