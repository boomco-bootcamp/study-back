package com.lecture.study.biz.service.user.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserFindReqVO")
public class UserFindReqVO {
    // 유저 ID
    private String userId;
    // 유저 확인 이메일 주소
    private String userEml;
}
