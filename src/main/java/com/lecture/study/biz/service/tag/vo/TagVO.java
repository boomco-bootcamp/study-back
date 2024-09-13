package com.lecture.study.biz.service.tag.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 스터디 태그 목록
 * STDY_TAG_L
 */
@Data
@Alias("TagVO")
public class TagVO {

	// 스터디 태그 ID
	private String stdyTagId;

	// 스터디 ID
	private String stdyId;

	// 스터티 태그 내용
	private String stdyTagCon;

	// 삭제 여부	여부
	private String delYn;

	// 등록 사용자 ID
	private String rgsnUserId;


	// 등록 일시
	private String rgsnTs;

	// 수정 사용자 ID
	private String amnnUserId;


	// 수정 일시
	private String amnnTs;
}
