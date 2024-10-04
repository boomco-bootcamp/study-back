package com.lecture.study.biz.service.mail.vo;

import lombok.Data;

@Data
public class FindPwMailSendVO extends MailSendVO {

    // 유저 패스워드
    private String userPswd;
}
