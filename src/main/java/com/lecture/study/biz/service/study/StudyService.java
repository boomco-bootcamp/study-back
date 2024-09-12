package com.lecture.study.biz.service.study;

import com.lecture.study.biz.service.study.vo.StudyReqVO;
import com.lecture.study.biz.service.study.vo.StudyResVO;
import com.lecture.study.biz.service.study.vo.StudySaveReqVO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 스터디 관련 서비스
 */
public interface StudyService {

    List<StudyResVO> searchStudyInfo(StudyReqVO reqVO) throws Exception;

    int saveStudyInfo(StudySaveReqVO saveReqVO) throws Exception;
}
