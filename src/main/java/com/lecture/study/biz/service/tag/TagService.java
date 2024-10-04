package com.lecture.study.biz.service.tag;

import com.lecture.study.biz.service.tag.vo.MyTagSaveReqVO;
import com.lecture.study.biz.service.tag.vo.MyTagVO;
import com.lecture.study.biz.service.tag.vo.TagVO;

import java.util.List;

public interface TagService {

    /**
     * 스터디 태그 목록 조회
     * @param stdyId
     * @return
     * @throws Exception
     */
    List<TagVO> searchTagList(String stdyId) throws Exception;

    /**
     * 인기 스터디 태그 목록 조회
     * @return
     * @throws Exception
     */
    List<TagVO> searchTagFavoriteList() throws Exception;

    /**
     * 관심 태그 목록 조회
     * @param loginUserId
     * @return
     * @throws Exception
     */
    List<MyTagVO> searchMyTagList(String loginUserId) throws Exception;

    /**
     * 스터디 태그 저장
     * @param tag
     * @return
     * @throws Exception
     */
    int insertTag(TagVO tag) throws Exception;

    /**
     * 스터디 태그 삭제
     * @param tag
     * @return
     * @throws Exception
     */
    int deleteTag(TagVO tag) throws Exception;

    /**
     * 관심 태그 추가
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    int addMyTag(MyTagSaveReqVO saveReqVO) throws Exception;

    /**
     * 관심 태그 삭제
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    int deleteMyTag(MyTagSaveReqVO saveReqVO) throws Exception;

}
