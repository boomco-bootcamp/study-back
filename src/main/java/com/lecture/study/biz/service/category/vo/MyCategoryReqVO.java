package com.lecture.study.biz.service.category.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("MyCategoryReqVO")
public class MyCategoryReqVO {

    // 유저 ID
    private String userId;

    // 스터디 카테고리 ID
    private String stdyCatId;
}
