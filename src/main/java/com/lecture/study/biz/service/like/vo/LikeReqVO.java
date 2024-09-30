package com.lecture.study.biz.service.like.vo;

import com.lecture.study.biz.service.comon.vo.PageVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("LikeReqVO")
public class LikeReqVO extends PageVO {

    // 로그인 UserId
    private String loginUserId;
}
