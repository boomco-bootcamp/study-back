package com.lecture.study.biz.controller.comment;

import com.lecture.study.biz.service.comment.CommentService;
import com.lecture.study.biz.service.comment.vo.*;
import com.lecture.study.biz.service.comon.vo.PagingListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/comment", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 스터디 댓글 목록 조회
     * @param reqVO
     * @return
     */
    @GetMapping("/study/list")
    public ResponseEntity searchStudyCommentList(StudyCommentReqVO reqVO) {
        try {
            PagingListVO<StudyCommentResVO> resultList = commentService.searchStudyCommentList(reqVO);
            return ResponseEntity.ok().body(resultList);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 댓글 작성
     * @param saveReqVO
     * @return
     */
    @PostMapping("/study/save")
    public ResponseEntity saveStudyComment(@RequestBody StudyCommentSaveReqVO saveReqVO) {
        try {
            int result = commentService.saveStudyComment(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 댓글 삭제
     * @param saveReqVO
     * @return
     */
    @PostMapping("/study/delete")
    public ResponseEntity deleteStudyComment(@RequestBody StudyCommentSaveReqVO saveReqVO) {
        try {
            int result = commentService.deleteStudyComment(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 커뮤니티 댓글 목록 조회
     * @param reqVO
     * @return
     */
    @GetMapping("/community/list")
    public ResponseEntity searchCommunityCommentList(CommunityCommentReqVO reqVO) {
        try {
            PagingListVO<CommunityCommentResVO> resultList = commentService.searchCommunityCommentList(reqVO);
            return ResponseEntity.ok().body(resultList);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 커뮤니티 댓글 작성
     * @param saveReqVO
     * @return
     */
    @PostMapping("/community/save")
    public ResponseEntity saveCommunityComment(@RequestBody CommunityCommentSaveReqVO saveReqVO) {
        try {
            int result = commentService.saveCommunityComment(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 커뮤니티 댓글 삭제
     * @param saveReqVO
     * @return
     */
    @PostMapping("community/delete")
    public ResponseEntity deleteCommunityComment(@RequestBody CommunityCommentSaveReqVO saveReqVO) {
        try {
            int result = commentService.deleteCommunityComment(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
