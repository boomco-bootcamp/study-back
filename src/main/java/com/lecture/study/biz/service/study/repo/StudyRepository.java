package com.lecture.study.biz.service.study.repo;

import com.lecture.study.biz.service.study.vo.StudyReqVO;
import com.lecture.study.biz.service.study.vo.StudyResVO;
import com.lecture.study.biz.service.study.vo.StudySaveReqVO;
import com.lecture.study.biz.service.study.vo.StudyTagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudyRepository {

    /**************************************************
     * 스터디 정보
     **************************************************/

    // 스터디 정보 목록조회
    List<StudyResVO> selectStudyInfoList(StudyReqVO reqVO);

    // 스터디 상세 정보 조회
    StudyResVO selectStudyInfo(@Param("stdyId")String stdyId);

    // 스터디 정보 작성
    int insertStudyInfo(StudySaveReqVO saveReqVO);

    // 스터디 정보 수정
    int updateStudyInfo(StudySaveReqVO saveReqVO);

    // 스터디 정보 삭제
    int deleteStudyInfo(StudySaveReqVO saveReqVO);


    /**************************************************
     * 스터디 태그
     **************************************************/

    // 스터디 태그 목록 조회
    List<StudyTagVO> selectStudyTagList(@Param("stdyId")String stdyId);

    // 스터디 태그 저장
    int insertStudyTag(StudyTagVO studyTagVO);

    // 스터디 태그 삭제
    int deleteStudyTag(StudyTagVO studyTagVO);





}
