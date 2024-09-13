package com.lecture.study.biz.service.comon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("PageVO")
public class PageVO {

    // pageNum, 현재 페이지
    private Integer page = 1;

    // amount, 페이지당 보여줄 데이터
    private Integer record = 10;

    // 하단 표시 page size
    private Integer pageSize = 10;
}
