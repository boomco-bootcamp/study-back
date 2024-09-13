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
            if(StringUtils.isBlank(reqVO.getStdyCatNm())) return 0;
            // UUID 생성
            reqVO.setStdyCatId(UUID.randomUUID().toString());
            return categoryRepository.insertCategoryInfo(reqVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
