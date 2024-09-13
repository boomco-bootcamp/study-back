package com.lecture.study.biz.service.member;

import com.lecture.study.biz.service.member.repo.MemberRepository;
import com.lecture.study.biz.service.member.vo.MemberResVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        List<MemberResVO> resultList = memberRepository.selectMemberList(stdyId);

        return resultList;
    }
}
