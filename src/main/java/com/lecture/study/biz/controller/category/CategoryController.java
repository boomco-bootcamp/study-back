package com.lecture.study.biz.controller.category;

import com.lecture.study.biz.service.category.CategoryService;
import com.lecture.study.biz.service.category.vo.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/category", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 스터디 카테고리 전체 목록 조회
     * @return
     */
    @GetMapping("/list/all")
    public ResponseEntity searchAllCategoryList() {
        try {
            List<CategoryVO> resultList = categoryService.searchAllCategoryList();
            return ResponseEntity.ok().body(resultList);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 스터디 카테고리 작성
     * @param reqVO
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity saveCategoryInfo(@RequestBody CategoryVO reqVO) {
        try {
            int result = categoryService.saveCategoryInfo(reqVO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
