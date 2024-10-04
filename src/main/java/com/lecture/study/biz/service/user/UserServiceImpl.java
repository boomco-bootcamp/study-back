package com.lecture.study.biz.service.user;

import com.lecture.study.app.utils.EncryptionUtils;
import com.lecture.study.app.utils.JwtTokenUtil;
import com.lecture.study.biz.service.sns.kakao.KakaoService;
import com.lecture.study.biz.service.sns.kakao.vo.KakaoUserInfoVO;
import com.lecture.study.biz.service.mail.MailService;
import com.lecture.study.biz.service.mail.vo.MailSendVO;
import com.lecture.study.biz.service.user.repo.UserRepository;
import com.lecture.study.biz.service.user.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
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

    private final KakaoService kakaoService;

    public final MailService mailService;

    public UserServiceImpl(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, KakaoService kakaoService, MailService mailService) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.kakaoService = kakaoService;
        this.mailService = mailService;
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
     * 로그인 for kakao
     * @param kakaoCode
     * @return
     * @throws Exception
     */
    @Override
    public String loginKakao(String kakaoCode) throws Exception {
        try {

            String jwt = "";

            // 카카오 code 로 accessToken 발급
            String accessToken = kakaoService.getAccessToken(kakaoCode);

            // 발급 받은 accessToken 으로 kakao 유저정보 조회
            KakaoUserInfoVO kakaoUserInfo = kakaoService.getUserInfo(accessToken);

            // kakao 유저 정보 파라메터 체크
            if(StringUtils.isBlank(kakaoUserInfo.getId())) throw new Exception("회원 정보가 존재하지 않습니다.");

            // 해당 유저가 회원 가입이 되어있는지 체크
            UserReqVO reqVO = new UserReqVO();
            reqVO.setUserSnsId(kakaoUserInfo.getId());
            reqVO.setUserSnsType("kakao");
            UserInfoVO userInfoVO = userRepository.selectUserInfoForSNS(reqVO);

            // 회원 가입이 되어있는 경우, jwt 토큰 발급
            if(userInfoVO != null) {
                jwt = jwtTokenUtil.makeJwt(userInfoVO.getUserId());
            }

            // 회원 가입이 되어있지 않은 경우, 회원 가입 후, jwt 토큰 발급
            else {
                // 회원 가입
                UserSaveReqVO saveReqVO = new UserSaveReqVO();
                // 랜덤 문자, 숫자 ID 생성
                String generatedString = RandomStringUtils.randomAlphanumeric(15);
                saveReqVO.setUserId(generatedString);
                saveReqVO.setUserNm(kakaoUserInfo.getNickname());
                saveReqVO.setUserSnsId(kakaoUserInfo.getId());
                saveReqVO.setUserSnsType("kakao");

                int insertResult = userRepository.insertUserInfo(saveReqVO);
                if(insertResult != 1) throw new Exception("카카오 회원연동에 실패했습니다.");

                // 유저 정보 재조회
                userInfoVO = userRepository.selectUserInfoForSNS(reqVO);
                jwt = jwtTokenUtil.makeJwt(userInfoVO.getUserId());
            }

            return jwt;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 로그아웃 for kakao
     * @param kakaoCode
     * @return
     * @throws Exception
     */
    @Override
    public int logoutKakao(String kakaoCode) throws Exception {
        try {
            // 카카오 code 로 accessToken 발급
            String accessToken = kakaoService.getAccessToken(kakaoCode);

            kakaoService.logout(accessToken);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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

    /**
     * 패스워드 찾기(임시 패스워드 발급)
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public int findPassword(UserFindReqVO reqVO) throws Exception {
        try {
            // 입력 받은 ID, 이메일 파라메터 체크
            if(StringUtils.isBlank(reqVO.getUserId()) && StringUtils.isBlank(reqVO.getUserEml())) {
                throw new Exception("아이디 혹은 이메일을 입력해주세요");
            }

            // 입력 받은 메일 체크
            UserReqVO userReqVO = new UserReqVO();
            userReqVO.setUserId(reqVO.getUserId());
            UserInfoVO userInfoVO = this.searchUserInfo(userReqVO);
            // 회원정보 체크
            if(userInfoVO == null) throw new Exception("해당 유저가 존재하지 않습니다.");
            // 등록한 이메일과 입력 받은 이메일 일치 확인
            if(!userInfoVO.getUserEml().equals(reqVO.getUserEml())) throw new Exception("아이디 혹은 이메일을 확인해주세요.");

            // 임시 패드워드 발급 후, 유저 정보 변경
            String tempPw = RandomStringUtils.randomAlphanumeric(20);
            UserSaveReqVO saveReqVO = new UserSaveReqVO();
            saveReqVO.setUserId(userInfoVO.getUserId());
            saveReqVO.setUserPswd(tempPw);
            saveReqVO.setUserNm(userInfoVO.getUserNm());
            saveReqVO.setUserEml(userInfoVO.getUserEml());
            saveReqVO.setUserTel(userInfoVO.getUserTel());
            saveReqVO.setUserSnsType(userInfoVO.getUserSnsType());
            saveReqVO.setUserSnsId(userInfoVO.getUserSnsId());
            this.updateUserInfo(saveReqVO);

            // 임시 패스워드 이메일 발송
            MailSendVO mailSendVO = new MailSendVO();
            mailSendVO.setTo(userInfoVO.getUserEml());
            mailSendVO.setSubject("블로그 프로젝트 임시 패스워드 발급");

            // 이메일 발송 내용
            String emailHtml = "<html>\n" +
                    "<head>\n" +
                    "<title>임시 패스워드 발급</title>\n" +
                    "<style>\n" +
                    "    body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }\n" +
                    "    .container { background-color: #ffffff; border-radius: 8px; padding: 20px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }\n" +
                    "    h2 { color: #333; }\n" +
                    "    p { font-size: 16px; color: #555; }\n" +
                    "    .password { font-weight: bold; font-size: 20px; color: #007bff; }\n" +
                    "    .footer { margin-top: 20px; font-size: 12px; color: #aaa; }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class='container'>\n" +
                    "    <h2>안녕하세요!</h2>\n" +
                    "    <p>귀하의 요청에 따라 임시 패스워드가 발급되었습니다.</p>\n" +
                    "    <p>임시 패스워드: <span class='password'>" + tempPw + "</span></p>\n" +
                    "    <p>로그인 후 반드시 패스워드를 변경해 주시기 바랍니다.</p>\n" +
                    "    <p>감사합니다.</p>\n" +
                    "</div>\n" +
                    "<div class='footer'>\n" +
                    "    &copy; 2024 웅진 프로젝트. 모든 권리 보유.\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";

            mailSendVO.setContent(emailHtml);
            mailSendVO.setHtmlFlg(true);

            mailService.mailSend(mailSendVO);

            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
