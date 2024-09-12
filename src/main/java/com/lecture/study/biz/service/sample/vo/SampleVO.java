package com.lecture.study.biz.service.sample.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * 샘플 VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("SampleVO")
public class SampleVO {

    private String test;

}
