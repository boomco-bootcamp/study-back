package com.lecture.study.biz.service.like.repo;

import com.lecture.study.biz.service.like.vo.LikeReqVO;
import com.lecture.study.biz.service.like.vo.LikeResVO;
import com.lecture.study.biz.service.like.vo.LikeSaveReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeRepository {

    // 내가 좋아요 한 스터디 목록 조회
    List<LikeResVO> searchMyLikeList(LikeReqVO reqVO);

    // 내가 좋아요 한 스터디 토탈 카운트 조회
    int searchMyLikeTotal(LikeReqVO reqVO);

    // 좋아요 작성
    int insertLike(LikeSaveReqVO saveReqVO);

    // 좋아요 삭제
    int deleteLike(LikeSaveReqVO saveReqVO);
}
