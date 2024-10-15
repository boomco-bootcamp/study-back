package com.lecture.study.biz.controller.file;

import com.lecture.study.biz.service.file.FileService;
import com.lecture.study.biz.service.file.vo.FileSaveReqVO;
import com.lecture.study.biz.service.file.vo.FileVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(value = "/api/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 파일 정보 조회
     * @param fileId
     * @return
     */
    @GetMapping("/detail")
    public ResponseEntity searchFileInfo(@RequestParam("id") String fileId) {
        try {
            FileVO fileVO = fileService.searchFileInfo(fileId);
            return ResponseEntity.ok(fileVO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 파일 업로드
     * @param file
     * @param user
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal User user) {
        try {
            FileSaveReqVO reqVO = new FileSaveReqVO();
            reqVO.setFile(file);
            reqVO.setRgsnUserId(user.getUsername());
            reqVO.setAmnnUserId(user.getUsername());
            String fileId = fileService.uploadFile(reqVO);
            return ResponseEntity.ok(fileId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 파일 다운로드
     * @param fileId
     * @param response
     */
    @GetMapping("/download/{fileId}")
    public void downloadFile(@PathVariable("fileId") String fileId, HttpServletResponse response) throws Exception {
        fileService.downloadFile(fileId, response);
    }
}
