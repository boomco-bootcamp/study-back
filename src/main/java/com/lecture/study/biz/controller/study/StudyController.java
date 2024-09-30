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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
     * @param user
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity searchStudyInfoList(StudyReqVO reqVO, @AuthenticationPrincipal User user) {
        try {
            // 로그인이 되어있는 경우, loginUserId 셋팅(좋아요 검색 관련)
            if(user != null) reqVO.setLoginUserId(user.getUsername());

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
     * @param user
     * @return
     */
    @GetMapping("/detail")
    public ResponseEntity searchStudyInfo(StudyReqVO reqVO, @AuthenticationPrincipal User user) {
        try {
            // 로그인이 되어있는 경우, loginUserId 셋팅(좋아요 검색 관련)
            if(user != null) reqVO.setLoginUserId(user.getUsername());

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
    public ResponseEntity saveStudyInfo(@RequestBody StudySaveReqVO saveReqVO, @AuthenticationPrincipal User user) {
        try {
            if(user == null) throw new Exception("로그인이 필요한 서비스 입니다.");
            saveReqVO.setRgsnUserId(user.getUsername());
            saveReqVO.setAmnnUserId(user.getUsername());
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
    public ResponseEntity deleteStudyInfo(@RequestBody StudySaveReqVO saveReqVO, @AuthenticationPrincipal User user) {
        try {
            if(user == null) throw new Exception("로그인이 필요한 서비스 입니다.");
            saveReqVO.setRgsnUserId(user.getUsername());
            saveReqVO.setAmnnUserId(user.getUsername());
            int result = studyService.deleteStudyInfo(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
