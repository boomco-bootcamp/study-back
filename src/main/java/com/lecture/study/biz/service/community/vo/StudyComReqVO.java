package com.lecture.study.biz.service.community.vo;

import com.lecture.study.biz.service.comon.vo.PageVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 스터디 커뮤니티 조회 관련 Req VO
 */
@Data
@Alias("StudyComReqVO")
public class StudyComReqVO extends PageVO {

    // 스터디 ID
    private String stdyId;

    // 스터디 커뮤니티 상태
    private String stdyComSt;

    // 검색어(제목, 내용)
    private String searchCon;

    // 정렬 조건
    private String orderType;

    /*
     * 상세 검색 조건
     */
    // 스터디 커뮤니티 ID
    private String stdyComtId;
}
