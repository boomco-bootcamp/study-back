package com.lecture.study.biz.controller.like;

import com.lecture.study.biz.service.comon.vo.PagingListVO;
import com.lecture.study.biz.service.like.LikeService;
import com.lecture.study.biz.service.like.vo.LikeReqVO;
import com.lecture.study.biz.service.like.vo.LikeResVO;
import com.lecture.study.biz.service.like.vo.LikeSaveReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

/**
 * 좋아요 관련 Controller
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/like", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    /**
     * 좋아요 작성
     * @param saveReqVO
     * @param user
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity addLike(@RequestBody LikeSaveReqVO saveReqVO, @AuthenticationPrincipal User user) {
        try {
            if(user == null) throw new Exception("로그인이 필요한 서비스 입니다.");
            saveReqVO.setRgsnUserId(user.getUsername());
            saveReqVO.setAmnnUserId(user.getUsername());
            int result = likeService.addLike(saveReqVO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 좋아요 삭제
     * @param saveReqVO
     * @param user
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity deleteLike(@RequestBody LikeSaveReqVO saveReqVO, @AuthenticationPrincipal User user) {
        try {
            if(user == null) throw new Exception("로그인이 필요한 서비스 입니다.");
            saveReqVO.setRgsnUserId(user.getUsername());
            saveReqVO.setAmnnUserId(user.getUsername());
            int result = likeService.deleteLike(saveReqVO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
