package com.lecture.study.biz.service.study.vo;


import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 스터디 조회 관련 Req VO
 */
@Data
@Alias("StudyReqVO")
public class StudyReqVO {

    // 검색어(강의 명, 테그)
    private String searchCon;

    // 스터디 카테고리 ID
    private String stdyCatId;

    // 정렬 조건
    private String orderType;

    
}
