package com.lecture.study.biz.service.community.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 스터디 커뮤니티 첨부파일 목록 VO
 * Table : STDY_COM_FILE_L
 */
@Data
@Alias("StudyComFileVO")
public class StudyComFileVO {

    //스터디 커뮤니티 ID
    private String stdyComtId;

    //파일 ID
    private String fileId;

    //등록 사용자
    private String rgsnUserId;

    //등록 일시
    private String rgsnTs;

    //수정 사용자
    private String amnnUserId;

    //수정 일시
    private String amnnTs;


    /*
     * 첨부 파일 정보
     * Table : FILE_ATCH_M
     */
    // 파일 명
    private String fileNm;

    // 파일 확장자
    private String fileEnts;

    // 파일 유형
    private String filePtrn;

    // 파일 크기
    private int fileSize;
}
