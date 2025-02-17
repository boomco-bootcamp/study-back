package com.lecture.study.biz.controller.user;

import com.lecture.study.biz.service.study.vo.StudySaveReqVO;
import com.lecture.study.biz.service.user.UserService;
import com.lecture.study.biz.service.user.vo.*;
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
@RequestMapping(value = "/api/user", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 로그인
     * @param loginReqVO
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReqVO loginReqVO) {
        try {
            // 유저 ID 패스워드 받아서 JWT 토큰 생성
            String jwtToken = userService.login(loginReqVO);
            return ResponseEntity.ok().body(jwtToken);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 로그인 for kakao
     * @param loginReqVO
     * @return
     */
    @PostMapping("/login/kakao")
    public ResponseEntity loginKakao(@RequestBody LoginReqVO loginReqVO) {
        try {
            // 유저 ID 패스워드 받아서 JWT 토큰 생성
            String jwtToken = userService.loginKakao(loginReqVO.getCode());
            return ResponseEntity.ok().body(jwtToken);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 로그아웃 for kakao
     * @param loginReqVO
     * @return
     */
    @PostMapping("/logout/kakao")
    public ResponseEntity logoutKakao(@RequestBody LoginReqVO loginReqVO) {
        try {
            int result = userService.logoutKakao(loginReqVO.getCode());
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 유저 정보 조회
     * @return
     */
    @PostMapping("/info")
    public ResponseEntity searchUserInfo(@AuthenticationPrincipal User user) {
        try {

            // 로그인 체크
            if(user == null) {return ResponseEntity.badRequest().body("로그인이 필요한 서비스 입니다.");}

            UserReqVO userReqVO = new UserReqVO();
            userReqVO.setUserId(user.getUsername());
            UserInfoVO result = userService.searchUserInfo(userReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 회원가입
     * @param saveReqVO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity addUserInfo(@RequestBody UserSaveReqVO saveReqVO) {
        try {
            int result = userService.addUserInfo(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 회원 정보 변경
     * @param saveReqVO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity updateUserInfo(@RequestBody UserSaveReqVO saveReqVO, @AuthenticationPrincipal User user) {
        try {
            saveReqVO.setUserId(user.getUsername());
            int result = userService.updateUserInfo(saveReqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 회원 탈퇴
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity deleteUserInfo(@AuthenticationPrincipal User user) {
        try {
            int result = userService.deleteUserInfo(user.getUsername());
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 패스워드 찾기(임시 패스워드 발급)
     * @param reqVO
     * @return
     */
    @PostMapping("/find")
    public ResponseEntity findPassword(@RequestBody UserFindReqVO reqVO) {
        try {
            int result = userService.findPassword(reqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
