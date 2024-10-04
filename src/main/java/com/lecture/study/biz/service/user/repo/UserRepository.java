package com.lecture.study.biz.service.user.repo;

import com.lecture.study.biz.service.user.vo.UserInfoVO;
import com.lecture.study.biz.service.user.vo.UserReqVO;
import com.lecture.study.biz.service.user.vo.UserSaveReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

    // 유저 정보 조회
    UserInfoVO selectUserInfo(UserReqVO reqVO);

    // 회원 가입
    int insertUserInfo(UserSaveReqVO saveReqVO);

    // 회원 정보 변경
    int updateUserInfo(UserSaveReqVO saveReqVO);

    // 회원 탈퇴
    int deleteUserInfo(@Param("userId") String userId);

    /**************************************************
     * SNS 로그인 관련 repo
     **************************************************/
    // SNS 연동 유저 조회
    UserInfoVO selectUserInfoForSNS(UserReqVO reqVO);
}
