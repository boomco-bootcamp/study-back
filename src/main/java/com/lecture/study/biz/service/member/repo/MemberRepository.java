package com.lecture.study.biz.service.member.repo;

import com.lecture.study.biz.service.like.vo.LikeReqVO;
import com.lecture.study.biz.service.like.vo.LikeResVO;
import com.lecture.study.biz.service.member.vo.MemberResVO;
import com.lecture.study.biz.service.member.vo.MemberSaveReqVO;
import com.lecture.study.biz.service.member.vo.MyMemberReqVO;
import com.lecture.study.biz.service.member.vo.MyMemberResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberRepository {

    /**************************************************
     * 스터디 참여 관련
     **************************************************/

    // 스터티 참여 인원 조회
    List<MemberResVO> selectMemberList(@Param("stdyId") String stdyId);

    // 스터디 참여
    int insertMember(MemberSaveReqVO saveReqVO);

    // 스터디 참여 삭제
    int deleteMember(MemberSaveReqVO saveReqVO);


    /**************************************************
     * 마이페이지 스터디 참여 관련
     **************************************************/

    // 가입 스터디 목록 조회
    List<MyMemberResVO> selectMyMemberList(MyMemberReqVO memberReqVO);

    // 가입 스터디 토탈 카운트 조회
    int selectMyMemberTotal(MyMemberReqVO memberReqVO);
}
