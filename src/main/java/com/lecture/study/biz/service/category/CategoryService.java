package com.lecture.study.biz.service.category;


import com.lecture.study.biz.service.category.vo.CategoryVO;

import java.util.List;

public interface CategoryService {

    /**
     * 스터디 카테고리 전체 목록 조회
     * @return
     */
    List<CategoryVO> searchAllCategoryList();

    /**
     * 스터디 카테고리 작성
     * @param reqVO
     * @return
     * @throws Exception
     */
    int saveCategoryInfo(CategoryVO reqVO) throws Exception;

}
