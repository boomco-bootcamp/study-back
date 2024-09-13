package com.lecture.study.biz.service.study;

import com.lecture.study.biz.service.comon.vo.PagingListVO;
import com.lecture.study.biz.service.study.vo.StudyReqVO;
import com.lecture.study.biz.service.study.vo.StudyResVO;
import com.lecture.study.biz.service.study.vo.StudySaveReqVO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 스터디 관련 서비스
 */
public interface StudyService {

    /**
     * 스터디 목록 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    PagingListVO<StudyResVO> searchStudyInfoList(StudyReqVO reqVO) throws Exception;

    /**
     * 스터디 상세 정보 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    StudyResVO searchStudyInfo(StudyReqVO reqVO) throws Exception;

    /**
     * 스터디 작성 및 수정
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    int saveStudyInfo(StudySaveReqVO saveReqVO) throws Exception;

    /**
     * 스터디 삭제
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    int deleteStudyInfo(StudySaveReqVO saveReqVO) throws Exception;
}
