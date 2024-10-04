package com.lecture.study.biz.service.member;

import com.lecture.study.biz.service.comon.vo.PagingListVO;
import com.lecture.study.biz.service.member.repo.MemberRepository;
import com.lecture.study.biz.service.member.vo.MemberResVO;
import com.lecture.study.biz.service.member.vo.MemberSaveReqVO;
import com.lecture.study.biz.service.member.vo.MyMemberReqVO;
import com.lecture.study.biz.service.member.vo.MyMemberResVO;
import com.lecture.study.biz.service.tag.TagService;
import com.lecture.study.biz.service.tag.repo.TagRepository;
import com.lecture.study.biz.service.tag.vo.TagVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final TagService tagService;

    public MemberServiceImpl(MemberRepository memberRepository, TagService tagService) {
        this.memberRepository = memberRepository;
        this.tagService = tagService;
    }

    /**
     * 스터디 참여인원 조회
     * @param stdyId
     * @return
     */
    @Override
    public List<MemberResVO> searchMemberList(String stdyId) throws Exception {
        try {
            List<MemberResVO> resultList = memberRepository.selectMemberList(stdyId);
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 가입 스터디 목록 조회
     * @param memberReqVO
     * @return
     */
    @Override
    public PagingListVO<MyMemberResVO> searchMyMemberList(MyMemberReqVO memberReqVO) throws Exception {
        try {
            // 가입 스터디 목록 조회
            List<MyMemberResVO> resultList = memberRepository.selectMyMemberList(memberReqVO);
            // 가입 스터디 토탈 카운트 조회
            int totalCnt = memberRepository.selectMyMemberTotal(memberReqVO);

            for(MyMemberResVO x: resultList) {
                List<TagVO> tagList = tagService.searchTagList(x.getStdyId());
                x.setTagList(tagList);
            }

            return new PagingListVO<>(memberReqVO, resultList, totalCnt);
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
