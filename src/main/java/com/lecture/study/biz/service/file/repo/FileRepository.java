package com.lecture.study.biz.service.file.repo;

import com.lecture.study.biz.service.file.vo.FileSaveReqVO;
import com.lecture.study.biz.service.file.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileRepository {

    // 파일 정보 조회
    FileVO selectFileInfo(@Param("fileId") String fileId);

    // 파일 정보 등록
    int insertFileInfo(FileSaveReqVO fileVO);

}
