package com.lecture.study.biz.service.study;

import com.lecture.study.biz.service.study.repo.StudyRepository;
import com.lecture.study.biz.service.study.vo.StudyReqVO;
import com.lecture.study.biz.service.study.vo.StudyResVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("StudyService")
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;

    public StudyServiceImpl(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public List<StudyResVO> searchStudyInfo(StudyReqVO reqVO) throws Exception {
        try {

            List<StudyResVO> resultList = studyRepository.selectStudyInfo(reqVO);

            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
