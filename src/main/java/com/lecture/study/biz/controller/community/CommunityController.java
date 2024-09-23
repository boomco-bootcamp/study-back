package com.lecture.study.biz.controller.community;

import com.lecture.study.biz.service.community.CommunityService;
import com.lecture.study.biz.service.community.vo.StudyComReqVO;
import com.lecture.study.biz.service.community.vo.StudyComResVO;
import com.lecture.study.biz.service.community.vo.StudyComSaveReqVO;
import com.lecture.study.biz.service.comon.vo.PagingListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/community", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    /**
     * 스터디 커뮤니티 목록 조회
     * @param reqVO
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity searchStudyComList(StudyComReqVO reqVO) {
        try {
            PagingListVO<StudyComResVO> resultList = communityService.searchStudyComList(reqVO);
            return ResponseEntity.ok().body(resultList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 커뮤니티 상세 조회
     * @param reqVO
     * @return
     */
    @GetMapping("detail")
    public ResponseEntity searchStudyComInfo(StudyComReqVO reqVO) {
        try {
            StudyComResVO result = communityService.searchStudyComInfo(reqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 커뮤니티 작성 및 수정
     * @param saveReqVO
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity saveStudyComInfo(@RequestBody StudyComSaveReqVO saveReqVO) {
        try {
            int result = communityService.saveStudyComInfo(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 커뮤니티 삭제
     * @param saveReqVO
     * @return
     */
    @PostMapping("delete")
    public ResponseEntity deleteStudyComInfo(@RequestBody StudyComSaveReqVO saveReqVO) {
        try {
            int result = communityService.deleteStudyComInfo(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}