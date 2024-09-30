package com.lecture.study.biz.service.category;

import com.lecture.study.biz.service.category.repo.CategoryRepository;
import com.lecture.study.biz.service.category.vo.CategoryVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * 스터디 카테고리 전체 목록 조회
     * @return
     */
    @Override
    public List<CategoryVO> searchAllCategoryList() {
        try {
            List<CategoryVO> resultList = categoryRepository.selectAllCategoryList();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * 스터디 카테고리 작성
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public int saveCategoryInfo(CategoryVO reqVO) throws Exception {

        try {
            int result = 0;

            if(StringUtils.isBlank(reqVO.getStdyCatNm())) return result;

            // 카테고리 ID 가 존재하지 않는 경우, 작성
            if(StringUtils.isBlank(reqVO.getStdyCatId())) {
                // UUID 생성
                reqVO.setStdyCatId(UUID.randomUUID().toString());
                result = categoryRepository.insertCategoryInfo(reqVO);
            }

            // 카테고리 ID 가 존재하는 경우, 수정
            else {
                result = categoryRepository.updateCategoryInfo(reqVO);
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 스터디 카테고리 삭제
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public int deleteCategoryInfo(CategoryVO reqVO) throws Exception {
        try {
            return categoryRepository.deleteCategoryInfo(reqVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
