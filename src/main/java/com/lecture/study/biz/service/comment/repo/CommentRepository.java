package com.lecture.study.biz.service.comment.repo;

import com.lecture.study.biz.service.comment.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentRepository {

    // 스터디 댓글 목록 조회
    List<StudyCommentResVO> selectStudyCommentList(StudyCommentReqVO reqVO);

    // 스터디 댓글 카운트 조회
    int selectStudyCommentListCnt(@Param("stdyId")String stdyId);

    // 스터디 댓글 작성
    int insertStudyComment(StudyCommentSaveReqVO reqVO);

    // 스터디 댓글 삭제
    int deleteStudyComment(StudyCommentSaveReqVO reqVO);

    // 커뮤니티 댓글 목록 조회
    List<CommunityCommentResVO> selectCommunityCommentList(CommunityCommentReqVO reqVO);

    // 커뮤니티 댓글 카운트 조회
    int selectCommunityCommentListCnt(@Param("stdyComId")String stdyComId);

    // 커뮤니티 댓글 작성
    int insertCommunityComment(CommunityCommentSaveReqVO reqVO);

    // 커뮤니티 댓글 삭제
    int deleteCommunityComment(CommunityCommentSaveReqVO reqVO);
}
