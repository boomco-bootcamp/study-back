package com.lecture.study.biz.service.study.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("StudySaveReqVO")
public class StudySaveReqVO {

    // 스터디 ID
    private String stdyId;

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

}
