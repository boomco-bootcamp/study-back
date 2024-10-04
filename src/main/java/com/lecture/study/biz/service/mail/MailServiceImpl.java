package com.lecture.study.biz.service.mail;

import com.lecture.study.biz.service.mail.vo.MailSendVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String FROM_ADDRESS;


    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 메일 전송
     * @param mailSendVO
     * @throws Exception
     */
    @Override
    public void mailSend(MailSendVO mailSendVO) throws Exception {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            // 보내는 사람
            mailHandler.setFrom(FROM_ADDRESS);
            // 받는 사람
            mailHandler.setTo(mailSendVO.getTo());
            // 제목
            mailHandler.setSubject(mailSendVO.getSubject());
            // 내용
            mailHandler.setText(mailSendVO.getContent(), mailSendVO.isHtmlFlg());
            // 메일 발송
            mailHandler.send();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}