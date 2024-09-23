package com.lecture.study.biz.service.community;

import com.lecture.study.biz.service.community.vo.StudyComReqVO;
import com.lecture.study.biz.service.community.vo.StudyComResVO;
import com.lecture.study.biz.service.community.vo.StudyComSaveReqVO;
import com.lecture.study.biz.service.comon.vo.PagingListVO;

public interface CommunityService {

    /**
     * 스터디 커뮤니티 목록 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    PagingListVO<StudyComResVO> searchStudyComList(StudyComReqVO reqVO) throws Exception;

    /**
     * 스터디 커뮤니티 상세 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    StudyComResVO searchStudyComInfo(StudyComReqVO reqVO) throws Exception;

    /**
     * 스터디 커뮤니티 작성 및 수정
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    int saveStudyComInfo(StudyComSaveReqVO saveReqVO) throws Exception;

    /**
     * 스터디 커뮤니티 삭제
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    int deleteStudyComInfo(StudyComSaveReqVO saveReqVO) throws Exception;
}
