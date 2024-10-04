package com.lecture.study.biz.service.member.vo;

import com.lecture.study.biz.service.comon.vo.PageVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("MyMemberReqVO")
public class MyMemberReqVO extends PageVO {

    // 유저 ID
    private String userId;
}
