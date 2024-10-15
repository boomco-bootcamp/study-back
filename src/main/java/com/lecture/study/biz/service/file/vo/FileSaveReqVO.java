package com.lecture.study.biz.service.file.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

@Data
@Alias("FileSaveReqVO")
public class FileSaveReqVO {

    // 다운로드 된 파일
    private MultipartFile file;

    // 파일 ID
    private String fileId;

    // 파일 명
    private String fileNm;

    // 파일 경로
    private String filePath;

    // 파일 확장자
    private String fileEnts;

    // 파일 유형
    private String filePtrn;

    // 파일 크기
    private long fileSize;

    // 등록 사용자
    private String rgsnUserId;

    // 수정 사용자
    private String amnnUserId;
}