package com.lecture.study.biz.service.member;

import com.lecture.study.biz.service.member.repo.MemberRepository;
import com.lecture.study.biz.service.member.vo.MemberResVO;
import com.lecture.study.biz.service.member.vo.MemberSaveReqVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 스터디 참여인원 조회
     * @param stdyId
     * @return
     */
    @Override
    public List<MemberResVO> searchMemberList(String stdyId) {
        try {
            List<MemberResVO> resultList = memberRepository.selectMemberList(stdyId);
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 스터디 참여
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int addMember(MemberSaveReqVO saveReqVO) throws Exception {
        try {
            saveReqVO.setStdyMemberId(UUID.randomUUID().toString());
            int result = memberRepository.insertMember(saveReqVO);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 스터디 참여 삭제
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int deleteMember(MemberSaveReqVO saveReqVO) throws Exception {
        try {
            int result = memberRepository.deleteMember(saveReqVO);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
