package com.lecture.study.biz.service.category.repo;

import com.lecture.study.biz.service.category.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryRepository {

    // 스터디 카테고리 전체 목록 조회
    List<CategoryVO> selectAllCategoryList();

    // 스터디 카테고리 작성
    int insertCategoryInfo(CategoryVO reqVO);

}
