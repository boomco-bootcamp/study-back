package com.lecture.study.biz.service.like;

import com.lecture.study.biz.service.comon.vo.PagingListVO;
import com.lecture.study.biz.service.like.repo.LikeRepository;
import com.lecture.study.biz.service.like.vo.LikeReqVO;
import com.lecture.study.biz.service.like.vo.LikeResVO;
import com.lecture.study.biz.service.like.vo.LikeSaveReqVO;
import com.lecture.study.biz.service.tag.TagService;
import com.lecture.study.biz.service.tag.vo.TagVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    private final TagService tagService;

    public LikeServiceImpl(LikeRepository likeRepository, TagService tagService) {
        this.likeRepository = likeRepository;
        this.tagService = tagService;
    }

    /**
     * 관심 스터디 목록 조회
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public PagingListVO<LikeResVO> searchMyLikeList(LikeReqVO reqVO) throws Exception {
        try {
            // 관심 스터디 목록 조회
            List<LikeResVO> resultList = likeRepository.searchMyLikeList(reqVO);
            // 관심 스터디 토탈 카운트 조회
            int totalCnt = likeRepository.searchMyLikeTotal(reqVO);

            // 스터디 테그 조회
            for(LikeResVO x: resultList) {
                List<TagVO> tagList = tagService.searchTagList(x.getStdyId());
                x.setTagList(tagList);
            }

            return new PagingListVO(reqVO, resultList, totalCnt);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 좋아요 작성
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int addLike(LikeSaveReqVO saveReqVO) throws Exception {
        try {
            return likeRepository.insertLike(saveReqVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 좋아요 삭제
     * @param saveReqVO
     * @return
     * @throws Exception
     */
    @Override
    public int deleteLike(LikeSaveReqVO saveReqVO) throws Exception {
        try {
            return likeRepository.deleteLike(saveReqVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
