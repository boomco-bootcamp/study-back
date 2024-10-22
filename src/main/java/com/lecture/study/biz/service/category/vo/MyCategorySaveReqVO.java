package com.lecture.study.biz.service.category.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.util.List;

@Data
@Alias("MyCategorySaveReqVO")
public class MyCategorySaveReqVO {

    // 유저 ID
    private String userId;

    // 스터디 카테고리 ID
    private String stdyCatId;

    // 등록 사용자
    private String rgsnUserId;

    // 등록 일시
    private Timestamp rgsnTs;

    // 리스트 저장 시, 사용
    private List<MyCategorySaveReqVO> list;


}
