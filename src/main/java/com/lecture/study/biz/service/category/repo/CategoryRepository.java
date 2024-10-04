package com.lecture.study.biz.service.category.repo;

import com.lecture.study.biz.service.category.vo.CategoryVO;
import com.lecture.study.biz.service.category.vo.MyCategoryReqVO;
import com.lecture.study.biz.service.category.vo.MyCategorySaveReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryRepository {

    /**************************************************
     * 스터디 카테고리 정보
     **************************************************/

    // 스터디 카테고리 전체 목록 조회
    List<CategoryVO> selectAllCategoryList();

    // 스터디 카테고리 작성
    int insertCategoryInfo(CategoryVO reqVO);

    // 스터디 카테고리 수정
    int updateCategoryInfo(CategoryVO reqVO);

    // 스터디 카테고리 삭제
    int deleteCategoryInfo(CategoryVO reqVO);



    /**************************************************
     * 관심 카테고리 목록
     **************************************************/

    // 관심 카테고리 목록 조회
    List<CategoryVO> selectMyCategoryList(MyCategoryReqVO reqVO);

    // 관심 카테고리 등록
    int insertMyCategory(MyCategorySaveReqVO saveReqVO);

    // 관심 카테고리 삭제
    int deleteMyCategory(MyCategorySaveReqVO saveReqVO);

}
