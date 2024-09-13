package com.lecture.study.biz.service.comment;

import com.lecture.study.biz.service.comment.repo.CommentRepository;
import com.lecture.study.biz.service.comment.vo.*;
import com.lecture.study.biz.service.comon.vo.PagingListVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * 스터디 댓글 목록 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public PagingListVO<StudyCommentResVO> searchStudyCommentList(StudyCommentReqVO reqVO) throws Exception {
        try {
            // 스터디 댓글 목록 조회
            List<StudyCommentResVO> resultList = commentRepository.selectStudyCommentList(reqVO);
            // 스터디 댓글 카운트 조회
            int totalCnt = commentRepository.selectStudyCommentListCnt(reqVO.getStdyId());

            // 댓글 목록이 존재하는 경우, 자식 댓글 조회
            for (StudyCommentResVO x : resultList) {
                reqVO.setStdyParentCommentId(x.getStdyCommentId());
                reqVO.setPage(null); // 페이징 처리 하지 않음
                List<StudyCommentResVO> childList = commentRepository.selectStudyCommentList(reqVO);
                x.setStdyChildCommentList(childList);
            }

            return new PagingListVO<>(reqVO, resultList, totalCnt);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 스터디 댓글 작성
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public int saveStudyComment(StudyCommentSaveReqVO reqVO) throws Exception {
        try {
            // UUID 생성
            reqVO.setStdyCommentId(UUID.randomUUID().toString());
            return commentRepository.insertStudyComment(reqVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 스터디 댓글 삭제
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public int deleteStudyComment(StudyCommentSaveReqVO reqVO) throws Exception {
        try {
            return commentRepository.deleteStudyComment(reqVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 커뮤니티 댓글 목록 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public PagingListVO<CommunityCommentResVO> searchCommunityCommentList(CommunityCommentReqVO reqVO) throws Exception {
        try {
            // 커뮤니티 댓글 목록 조회
            List<CommunityCommentResVO> resultList = commentRepository.selectCommunityCommentList(reqVO);
            // 커뮤니티 댓글 카운트 조회
            int total = commentRepository.selectCommunityCommentListCnt(reqVO.getStdyComId());

            for (CommunityCommentResVO x : resultList) {
                reqVO.setStdyParentComCommentId(x.getStdyComCommentId());
                reqVO.setPage(null); // 페이징 처리 하지 않음
                List<CommunityCommentResVO> childList = commentRepository.selectCommunityCommentList(reqVO);
                x.setStdyChildComCommentList(childList);
            }

            return new PagingListVO<>(reqVO, resultList, total);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 커뮤니티 댓글 작성
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public int saveCommunityComment(CommunityCommentSaveReqVO reqVO) throws Exception {
        try {
            // UUID 생성
            reqVO.setStdyComCommentId(UUID.randomUUID().toString());
            return commentRepository.insertCommunityComment(reqVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 커뮤니티 댓글 삭제
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public int deleteCommunityComment(CommunityCommentSaveReqVO reqVO) throws Exception {
        try {
            return commentRepository.deleteCommunityComment(reqVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
