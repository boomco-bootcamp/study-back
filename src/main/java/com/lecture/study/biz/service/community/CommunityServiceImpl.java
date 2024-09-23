package com.lecture.study.biz.service.community;

import com.lecture.study.biz.service.community.repo.StudyComRepository;
import com.lecture.study.biz.service.community.vo.StudyComFileVO;
import com.lecture.study.biz.service.community.vo.StudyComReqVO;
import com.lecture.study.biz.service.community.vo.StudyComResVO;
import com.lecture.study.biz.service.community.vo.StudyComSaveReqVO;
import com.lecture.study.biz.service.comon.vo.PagingListVO;
import com.lecture.study.biz.service.member.MemberService;
import com.lecture.study.biz.service.member.vo.MemberResVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CommunityServiceImpl")
public class CommunityServiceImpl implements CommunityService {

    private final StudyComRepository studyComRepository;

    private final MemberService memberService;

    public CommunityServiceImpl(StudyComRepository studyComRepository, MemberService memberService) {
        this.studyComRepository = studyComRepository;
        this.memberService = memberService;
    }

    /**
     * 스터디 커뮤니티 목록 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public PagingListVO<StudyComResVO> searchStudyComList(StudyComReqVO reqVO) throws Exception {
        try {
            // 필수 파라메터 체크
            if(StringUtils.isBlank(reqVO.getStdyId())) return new PagingListVO<>();

            // 스터디 커뮤니티 목록 조회
            List<StudyComResVO> resultList = studyComRepository.selectStudyComList(reqVO);
            // 스터디 커뮤니티 목록 토탈 카운트 조회
            int totalCnt = studyComRepository.selectStudyComTotal(reqVO);

            return new PagingListVO<>(reqVO, resultList, totalCnt);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 스터디 커뮤니티 상세 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public StudyComResVO searchStudyComInfo(StudyComReqVO reqVO) throws Exception {
        try {
            // 필수 파라메터 체크
            if(StringUtils.isBlank(reqVO.getStdyComtId())) return new StudyComResVO();

            StudyComResVO result = studyComRepository.selectStudyComInfo(reqVO.getStdyComtId());

            if(result != null) {

                // 참여 맴버 조회
                List<MemberResVO> memberList = memberService.searchMemberList(result.getStdyId());
                result.setMemberList(memberList);

                // 첨부파일 조회
                List<StudyComFileVO> fileList = studyComRepository.selectStudyComFileList(result.getStdyComtId());
                result.setFileList(fileList);
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * 스터디 커뮤니티 작성 및 수정
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int saveStudyComInfo(StudyComSaveReqVO saveReqVO) throws Exception {
        try {
            int result = 0;

            // 필수 파라메터 체크
            if(StringUtils.isBlank(saveReqVO.getStdyId())) return result;

            // 스터디 커뮤니티 ID가 존재하지 않는 경우, 작성
            if(StringUtils.isBlank(saveReqVO.getStdyComtId())) {
                String stdyComtId = java.util.UUID.randomUUID().toString();
                saveReqVO.setStdyComtId(stdyComtId);
                result = studyComRepository.insertStudyComInfo(saveReqVO);

                // 첨부파일 목록 추가
                if(saveReqVO.getFileList() != null && saveReqVO.getFileList().size() > 0) {
                    for(StudyComFileVO fileVO : saveReqVO.getFileList()) {
                        fileVO.setStdyComtId(stdyComtId);
                        studyComRepository.insertStudyComFileInfo(fileVO);
                    }
                }
            }

            // 스터디 커뮤니티 ID가 존재하는 경우, 수정
            else {
                result = studyComRepository.updateStudyComInfo(saveReqVO);

                // 기존에 존재하는 첨부파일 목록 삭제
                studyComRepository.deleteStudyComFileInfo(saveReqVO.getStdyComtId());

                // 첨부파일 목록 추가
                if(saveReqVO.getFileList() != null && saveReqVO.getFileList().size() > 0) {
                    for(StudyComFileVO fileVO : saveReqVO.getFileList()) {
                        fileVO.setStdyComtId(saveReqVO.getStdyComtId());
                        studyComRepository.insertStudyComFileInfo(fileVO);
                    }
                }
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 스터디 커뮤니티 삭제
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int deleteStudyComInfo(StudyComSaveReqVO saveReqVO) throws Exception {
        try {
            int result = studyComRepository.deleteStudyComInfo(saveReqVO);

            // 기존에 존재하는 첨부파일 목록 삭제
            studyComRepository.deleteStudyComFileInfo(saveReqVO.getStdyComtId());

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
