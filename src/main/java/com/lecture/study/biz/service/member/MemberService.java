package com.lecture.study.biz.service.member;

import com.lecture.study.biz.service.member.vo.MemberResVO;

import java.util.List;

/**
 * 스터디 참여인원 관련 서비스
 */
public interface MemberService {

    /**
     * 스터디 참여인원 조회
     * @param stdyId
     * @return
     */
    List<MemberResVO> searchMemberList(String stdyId);

}
