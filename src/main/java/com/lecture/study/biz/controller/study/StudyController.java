package com.lecture.study.biz.controller.study;

import com.lecture.study.biz.service.comon.vo.PagingListVO;
import com.lecture.study.biz.service.study.StudyService;
import com.lecture.study.biz.service.study.vo.StudyReqVO;
import com.lecture.study.biz.service.study.vo.StudyResVO;
import com.lecture.study.biz.service.study.vo.StudySaveReqVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/study", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StudyController {

    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    /**
     * 스터디 목록 조회
     * @param reqVO
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity searchStudyInfoList(StudyReqVO reqVO) {
        try {
            PagingListVO<StudyResVO> resultList = studyService.searchStudyInfoList(reqVO);
            return ResponseEntity.ok().body(resultList);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 상세 정보 조회
     * @param reqVO
     * @return
     */
    @GetMapping("/detail")
    public ResponseEntity searchStudyInfo(StudyReqVO reqVO) {
        try {
            StudyResVO rsult = studyService.searchStudyInfo(reqVO);
            return ResponseEntity.ok().body(rsult);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 작성 및 수정
     * @param saveReqVO
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity saveStudyInfo(@RequestBody StudySaveReqVO saveReqVO) {
        try {
            int result = studyService.saveStudyInfo(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 삭제
     * @param saveReqVO
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity deleteStudyInfo(@RequestBody StudySaveReqVO saveReqVO) {
        try {
            int result = studyService.deleteStudyInfo(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
