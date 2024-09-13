package com.lecture.study.biz.service.member.repo;

import com.lecture.study.biz.service.member.vo.MemberResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberRepository {

    // 스터티 참여 인원 조회
    List<MemberResVO> selectMemberList(@Param("stdyId") String stdyId);



}
