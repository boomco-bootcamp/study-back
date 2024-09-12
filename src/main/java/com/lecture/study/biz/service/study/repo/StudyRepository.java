package com.lecture.study.biz.service.study.repo;

import com.lecture.study.biz.service.study.vo.StudyReqVO;
import com.lecture.study.biz.service.study.vo.StudyResVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyRepository {

    List<StudyResVO> selectStudyInfo(StudyReqVO reqVO);

}
