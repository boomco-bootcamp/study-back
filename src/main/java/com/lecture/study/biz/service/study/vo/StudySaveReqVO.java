package com.lecture.study.biz.service.study.vo;

import com.lecture.study.biz.service.tag.vo.TagVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.util.List;

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
    private Timestamp stdyStDtTm;

    // 스터디 종료일
    private String stdyEnDt;
    private Timestamp stdyEnDtTm;

    // 스터디 상태
    private String stdySt;

    // 스터디 카테고리 ID
    private String stdyCatId;

    // 스터티 태그 리스트
    private List<TagVO> tagList;

    // 등록 사용자
    private String rgsnUserId;

    // 수정 사용자
    private String amnnUserId;
}

