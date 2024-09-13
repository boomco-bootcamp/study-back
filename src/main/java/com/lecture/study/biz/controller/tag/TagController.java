package com.lecture.study.biz.controller.tag;

import com.lecture.study.biz.service.tag.TagService;
import com.lecture.study.biz.service.tag.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/tag", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 인기 스터디 태그 목록 조회
     * @return
     */
    @GetMapping("/list/favorite")
    public ResponseEntity searchTagFavoriteList() {
        try {
            List<TagVO> resultList = tagService.searchTagFavoriteList();
            return ResponseEntity.ok().body(resultList);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
