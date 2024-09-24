package com.lecture.study.biz.service.user;

import com.lecture.study.app.utils.EncryptionUtils;
import com.lecture.study.app.utils.JwtTokenUtil;
import com.lecture.study.biz.service.user.repo.UserRepository;
import com.lecture.study.biz.service.user.vo.LoginReqVO;
import com.lecture.study.biz.service.user.vo.UserInfoVO;
import com.lecture.study.biz.service.user.vo.UserReqVO;
import com.lecture.study.biz.service.user.vo.UserSaveReqVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 스터디 유저 관련 서비스 Impl
 */
@Slf4j
@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtTokenUtil jwtTokenUtil;

    public UserServiceImpl(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * 로그인
     * @param loginReqVO
     * @return
     * @throws Exception
     */
    @Override
    public String login(LoginReqVO loginReqVO) throws Exception {

        if(StringUtils.isBlank(loginReqVO.getUserId()) || StringUtils.isBlank(loginReqVO.getUserPswd())) {
            throw new Exception("아이디 혹은 패스워드를 입력해주세요.");
        }

        UserReqVO reqVO = new UserReqVO();
        reqVO.setUserId(loginReqVO.getUserId());
        reqVO.setPasswordYn("Y");
        UserInfoVO userInfo = this.searchUserInfo(reqVO);

        if(userInfo == null) {
            throw new Exception("해당 유저가 존재하지 않습니다.");
        }

        if(!EncryptionUtils.sha256Encode(loginReqVO.getUserPswd()).equals(userInfo.getUserPswd())){
            throw new Exception("아이디 혹은 패스워드를 확인해주세요.");
        }

        String jwt = jwtTokenUtil.makeJwt(userInfo.getUserId());

        return jwt;
    }

    /**
     * 유저 정보 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public UserInfoVO searchUserInfo(UserReqVO reqVO) throws Exception {
        try {
            UserInfoVO result = userRepository.selectUserInfo(reqVO);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 회원 가입
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int addUserInfo(UserSaveReqVO saveReqVO) throws Exception {
        try {

            UserReqVO userReqVO = new UserReqVO();
            userReqVO.setUserId(saveReqVO.getUserId());
            userReqVO.setDelCheck("Y");
            UserInfoVO userInfoVO = this.searchUserInfo(userReqVO);

            if(userInfoVO != null) throw new Exception("이미 존재하는 아이디 입니다.");

            // 패스워드 암호화
            saveReqVO.setUserPswd(EncryptionUtils.sha256Encode(saveReqVO.getUserPswd()));

            int result = userRepository.insertUserInfo(saveReqVO);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 회원 정보 변경
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int updateUserInfo(UserSaveReqVO saveReqVO) throws Exception {
        try {
            if(!StringUtils.isBlank(saveReqVO.getUserPswd())) {
                // 패스워드 암호화
                saveReqVO.setUserPswd(EncryptionUtils.sha256Encode(saveReqVO.getUserPswd()));
            }
            int result = userRepository.updateUserInfo(saveReqVO);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 회원 탈퇴
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public int deleteUserInfo(String userId) throws Exception {
        try {
            int result = userRepository.deleteUserInfo(userId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
