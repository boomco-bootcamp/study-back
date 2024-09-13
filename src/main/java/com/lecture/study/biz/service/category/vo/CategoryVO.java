package com.lecture.study.biz.service.category.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("CategoryVO")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO {

    // 스터디 카테고리 ID
    private String stdyCatId;

    // 스터디 카테고리 명
    private String stdyCatNm;

    // 삭제 여부
    private String delYn;

    // 등록 사용자
    private String rgsnUserId;

    // 등록 일시
    private Timestamp rgsnTs;

    // 수정 사용자
    private String amnnUserId;

    // 수정 일시
    private Timestamp amnnTs;

}
