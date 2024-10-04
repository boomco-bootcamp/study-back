package com.lecture.study.biz.service.like;

import com.lecture.study.biz.service.comon.vo.PagingListVO;
import com.lecture.study.biz.service.like.vo.LikeReqVO;
import com.lecture.study.biz.service.like.vo.LikeResVO;
import com.lecture.study.biz.service.like.vo.LikeSaveReqVO;

import java.io.IOException;

/**
 * 좋아요 관련 Interface Service
 */
public interface LikeService {

    /**
     * 관심 스터디 목록 조회
     * @param likeReqVO
     * @return
     */
    PagingListVO<LikeResVO> searchMyLikeList(LikeReqVO likeReqVO) throws Exception;

    /**
     * 좋아요 작성
     * @param likeSaveVO
     * @return
     * @throws Exception
     */
    int addLike(LikeSaveReqVO likeSaveVO) throws Exception;

    /**
     * 좋아요 삭제
     * @param likeSaveVO
     * @return
     * @throws Exception
     */
    int deleteLike(LikeSaveReqVO likeSaveVO)throws Exception;








}
