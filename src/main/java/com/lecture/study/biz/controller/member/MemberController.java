package com.lecture.study.biz.controller.member;

import com.lecture.study.biz.service.member.MemberService;
import com.lecture.study.biz.service.member.vo.MemberSaveReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/member", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 스터디 참여
     * @param saveReqVO
     * @param user
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity addMember(@RequestBody MemberSaveReqVO saveReqVO, @AuthenticationPrincipal User user) {
        try {
            if(user == null) throw new Exception("로그인이 필요한 서비스 입니다.");
            saveReqVO.setUserId(user.getUsername());
            saveReqVO.setRgsnUserId(user.getUsername());
            saveReqVO.setAmnnUserId(user.getUsername());
            int result = memberService.addMember(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 참여 삭제
     * @param saveReqVO
     * @param user
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity deleteMember(@RequestBody MemberSaveReqVO saveReqVO, @AuthenticationPrincipal User user) {
        try {
            if(user == null) throw new Exception("로그인이 필요한 서비스 입니다.");
            saveReqVO.setUserId(user.getUsername());
            saveReqVO.setRgsnUserId(user.getUsername());
            saveReqVO.setAmnnUserId(user.getUsername());
            int result = memberService.deleteMember(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
