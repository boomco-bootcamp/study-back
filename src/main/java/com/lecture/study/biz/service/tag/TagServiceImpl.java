package com.lecture.study.biz.service.tag;

import com.lecture.study.biz.service.tag.repo.TagRepository;
import com.lecture.study.biz.service.tag.vo.MyTagSaveReqVO;
import com.lecture.study.biz.service.tag.vo.MyTagVO;
import com.lecture.study.biz.service.tag.vo.TagVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("TagService")
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * 스터디 태그 목록 조회
     * @param stdyId
     * @return
     * @throws Exception
     */
    @Override
    public List<TagVO> searchTagList(String stdyId) throws Exception {
        try {
            List<TagVO> resultList = tagRepository.selectStudyTagList(stdyId);
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 인기 스터디 태그 목록 조회
     * @return
     * @throws Exception
     */
    @Override
    public List<TagVO> searchTagFavoriteList() throws Exception {
        try {
            List<TagVO> resultList = tagRepository.selctTagFavoriteList();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 관심 태그 목록 조회
     * @param loginUserId
     * @return
     * @throws Exception
     */
    @Override
    public List<MyTagVO> searchMyTagList(String loginUserId) throws Exception {
        try {
            // 관심 태그 목록 조회
            List<MyTagVO> resultList = tagRepository.selectMyTagList(loginUserId);
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 스터디 태그 저장
     * @param tag
     * @return
     * @throws Exception
     */
    @Override
    public int insertTag(TagVO tag) throws Exception {
        try {
            return tagRepository.insertStudyTag(tag);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 스터디 태그 삭제
     * @param tag
     * @return
     * @throws Exception
     */
    @Override
    public int deleteTag(TagVO tag) throws Exception {
        try {
            return tagRepository.deleteStudyTag(tag);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 관심 태그 추가
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int addMyTag(MyTagSaveReqVO saveReqVO) throws Exception {
        try {
            saveReqVO.setStdyLikeTagId(UUID.randomUUID().toString());
            return tagRepository.insertMyTag(saveReqVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 관심 태그 삭제
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int deleteMyTag(MyTagSaveReqVO saveReqVO) throws Exception {
        try {
            return tagRepository.deleteMyTag(saveReqVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
