package com.lecture.study.biz.service.study;

import com.lecture.study.app.constant.StudyStatusCode;
import com.lecture.study.app.utils.StringUtil;
import com.lecture.study.biz.service.study.repo.StudyRepository;
import com.lecture.study.biz.service.study.vo.StudyReqVO;
import com.lecture.study.biz.service.study.vo.StudyResVO;
import com.lecture.study.biz.service.study.vo.StudySaveReqVO;
import com.lecture.study.biz.service.study.vo.StudyTagVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service("StudyService")
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;

    public StudyServiceImpl(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    /**
     * 스터디 목록 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public List<StudyResVO> searchStudyInfoList(StudyReqVO reqVO) throws Exception {
        try {
            // 스터디 조회
            List<StudyResVO> resultList = studyRepository.selectStudyInfoList(reqVO);

            // 스터디 테그 조회
            for(StudyResVO x : resultList){
                List<StudyTagVO> tagList = studyRepository.selectStudyTagList(x.getStdyId());
                x.setTagList(tagList);
            }

            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 스터디 상세 정보 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public StudyResVO searchStudyInfo(StudyReqVO reqVO) throws Exception {

        try {

            if(StringUtils.isBlank(reqVO.getStdyId())) return new StudyResVO();

            // 스터디 상세 정보 조회
            StudyResVO result = studyRepository.selectStudyInfo(reqVO.getStdyId());

            // 태그 리스트 조회
            if(result != null) {
                List<StudyTagVO> tagList = studyRepository.selectStudyTagList(result.getStdyId());
                result.setTagList(tagList);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 스터디 작성 및 수정
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int saveStudyInfo(StudySaveReqVO saveReqVO) throws Exception {
        try {

            int result = 0;

            // 스터디 ID 가 존재하지 않는 경우, 작성
            if(StringUtils.isBlank(saveReqVO.getStdyId())) {
                // UUID 발급
                String stdyId = UUID.randomUUID().toString();
                saveReqVO.setStdyId(stdyId);
                saveReqVO.setStdySt(StudyStatusCode.STUDY_NOT_STARTED.getCode()); // 모집 중
                saveReqVO.setStdyStDtTm(StringUtil.convertStringToTimestamp(saveReqVO.getStdyStDt()));
                saveReqVO.setStdyEnDtTm(StringUtil.convertStringToTimestamp(saveReqVO.getStdyEnDt()));
                result = studyRepository.insertStudyInfo(saveReqVO);

                // 태그 리스트가 존재하는 경우, 태그 저장
                if(saveReqVO.getTagList() != null && saveReqVO.getTagList().size() > 0) {
                    for (StudyTagVO tag : saveReqVO.getTagList()) {
                        tag.setStdyTagId(UUID.randomUUID().toString());
                        tag.setStdyId(stdyId);
                        tag.setDelYn("N");
                        studyRepository.insertStudyTag(tag);
                    }
                }

            }
            // 스터디 ID 가 존재하는 경우, 수정
            else {
                saveReqVO.setStdyStDtTm(StringUtil.convertStringToTimestamp(saveReqVO.getStdyStDt()));
                saveReqVO.setStdyEnDtTm(StringUtil.convertStringToTimestamp(saveReqVO.getStdyEnDt()));
                result = studyRepository.updateStudyInfo(saveReqVO);

                // 기존에 있는 태그를 전부 삭제처리 하고,
                StudyTagVO studyTagVO = new StudyTagVO();
                studyTagVO.setStdyId(saveReqVO.getStdyId());
                studyRepository.deleteStudyTag(studyTagVO);

                // 새 태그 리스트가 존재하는 경우, 태그 저장
                if(saveReqVO.getTagList() != null && saveReqVO.getTagList().size() > 0) {
                    for (StudyTagVO tag : saveReqVO.getTagList()) {
                        tag.setStdyTagId(UUID.randomUUID().toString());
                        tag.setStdyId(saveReqVO.getStdyId());
                        tag.setDelYn("N");
                        studyRepository.insertStudyTag(tag);
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
     * 스터디 삭제
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int deleteStudyInfo(StudySaveReqVO saveReqVO) throws Exception {
        try {
            int result = studyRepository.deleteStudyInfo(saveReqVO);

            // 태그 전체 삭제
            StudyTagVO studyTagVO = new StudyTagVO();
            studyTagVO.setStdyId(saveReqVO.getStdyId());
            studyRepository.deleteStudyTag(studyTagVO);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
