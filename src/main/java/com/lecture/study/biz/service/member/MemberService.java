package com.lecture.study.biz.service.member;

import com.lecture.study.biz.service.member.vo.MemberResVO;
import com.lecture.study.biz.service.member.vo.MemberSaveReqVO;

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

    /**
     * 스터디 참여
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    int addMember(MemberSaveReqVO saveReqVO) throws Exception;

    /**
     * 스터디 참여 취소
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    int deleteMember(MemberSaveReqVO saveReqVO) throws Exception;

}
