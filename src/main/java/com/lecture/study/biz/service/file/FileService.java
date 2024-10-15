package com.lecture.study.biz.service.file;

import com.lecture.study.biz.service.file.vo.FileSaveReqVO;
import com.lecture.study.biz.service.file.vo.FileVO;
import jakarta.servlet.http.HttpServletResponse;

public interface FileService {
    /**
     * 파일 정보 조회
     * @param fileId
     * @return
     */
    FileVO searchFileInfo(String fileId) throws Exception;

    /**
     * 파일 업로드
     * @param reqVO
     * @return
     */
    String uploadFile(FileSaveReqVO reqVO) throws Exception;

    /**
     * 파일 다운로드
     * @param fileId
     * @param response
     * @throws Exception
     */
    void downloadFile(String fileId, HttpServletResponse response) throws Exception;
}
