package com.lecture.study.biz.service.community.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("StudyComSaveReqVO")
public class StudyComSaveReqVO {

    // 스터디 커뮤니티 ID
    private String stdyComtId;

    // 스터디 ID
    private String stdyId;

    // 스터티 커뮤니티 제목
    private String stdyComTitle;

    // 스터디 커뮤니티 내용
    private String stdyComCon;

    // 스터디 커뮤니티 상태
    private String stdyComSt;

    // 스터디 커뮤니티 첨부파일 리스트
    private List<StudyComFileVO> fileList;

    // 등록 사용자
    private String rgsnUserId;

    // 수정 사용자
    private String amnnUserId;
}
