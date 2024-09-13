package com.lecture.study.biz.service.tag;

import com.lecture.study.biz.service.tag.repo.TagRepository;
import com.lecture.study.biz.service.tag.vo.TagVO;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<TagVO> resultList = tagRepository.selectStudyTagList(stdyId);
        return resultList;
    }

    /**
     * 인기 스터디 태그 목록 조회
     * @return
     * @throws Exception
     */
    @Override
    public List<TagVO> searchTagFavoriteList() throws Exception {
        List<TagVO> resultList = tagRepository.selctTagFavoriteList();
        return resultList;
    }

    /**
     * 스터디 태그 저장
     * @param tag
     * @return
     * @throws Exception
     */
    @Override
    public int insertTag(TagVO tag) throws Exception {
        return tagRepository.insertStudyTag(tag);
    }

    /**
     * 스터디 태그 삭제
     * @param tag
     * @return
     * @throws Exception
     */
    @Override
    public int deleteTag(TagVO tag) throws Exception {
        return tagRepository.deleteStudyTag(tag);
    }
}
