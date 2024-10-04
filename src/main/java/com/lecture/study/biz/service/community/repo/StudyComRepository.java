package com.lecture.study.biz.service.community.repo;

import com.lecture.study.biz.service.community.vo.StudyComFileVO;
import com.lecture.study.biz.service.community.vo.StudyComReqVO;
import com.lecture.study.biz.service.community.vo.StudyComResVO;
import com.lecture.study.biz.service.community.vo.StudyComSaveReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudyComRepository {

    /**************************************************
     * 스터디 커뮤니티 정보
     **************************************************/

    // 스터디 커뮤니티 목록 조회
    List<StudyComResVO> selectStudyComList(StudyComReqVO reqVO);

    // 스터디 커뮤니티 목록 토탈 카운트 조회
    int selectStudyComTotal(StudyComReqVO reqVO);

    // 스터디 커뮤니티 상세 조회
    StudyComResVO selectStudyComInfo(@Param("stdyComtId") String stdyComtId);

    // 스터디 커뮤니티 작성
    int insertStudyComInfo(StudyComSaveReqVO reqVO);

    // 스터디 커뮤니티 수정
    int updateStudyComInfo(StudyComSaveReqVO reqVO);

    // 스터디 커뮤니티 삭제
    int deleteStudyComInfo(StudyComSaveReqVO reqVO);


    /**************************************************
     * 스터디 커뮤니티 첨부파일
     **************************************************/

    // 스터디 첨부파일 목록 조회
    List<StudyComFileVO> selectStudyComFileList(@Param("stdyComtId") String stdyComtId);

    // 스터디 커뮤니티 첨부파일 작성
    int insertStudyComFileInfo(StudyComFileVO fileVO);

    // 스터디 커뮤니티 첨부파일 삭제
    int deleteStudyComFileInfo(@Param("stdyComtId") String stdyComtId);
}
