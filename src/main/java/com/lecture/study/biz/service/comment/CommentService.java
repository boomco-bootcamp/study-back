package com.lecture.study.biz.service.comment;

import com.lecture.study.biz.service.comment.vo.*;
import com.lecture.study.biz.service.comon.vo.PagingListVO;

public interface CommentService {

    /**
     * 스터디 댓글 목록 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    PagingListVO<StudyCommentResVO> searchStudyCommentList(StudyCommentReqVO reqVO) throws Exception;

    /**
     * 스터디 댓글 작성
     * @param reqVO
     * @return
     * @throws Exception
     */
    int saveStudyComment(StudyCommentSaveReqVO reqVO) throws Exception;

    /**
     * 스터디 댓글 삭제
     * @param reqVO
     * @return
     * @throws Exception
     */
    int deleteStudyComment(StudyCommentSaveReqVO reqVO) throws Exception;

    /**
     * 커뮤니티 댓글 목록 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    PagingListVO<CommunityCommentResVO> searchCommunityCommentList(CommunityCommentReqVO reqVO) throws Exception;

    /**
     * 커뮤니티 댓글 작성
     * @param reqVO
     * @return
     * @throws Exception
     */
    int saveCommunityComment(CommunityCommentSaveReqVO reqVO) throws Exception;

    /**
     * 커뮤니티 댓글 삭제
     * @param reqVO
     * @return
     * @throws Exception
     */
    int deleteCommunityComment(CommunityCommentSaveReqVO reqVO) throws Exception;



}
