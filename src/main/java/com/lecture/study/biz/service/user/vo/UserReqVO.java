package com.lecture.study.biz.service.user.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserReqVO")
public class UserReqVO {

    // 유저 ID
    private String userId;

    // 패스워드 여부
    private String passwordYn;

    // 삭제 체크
    private String delCheck;


}
