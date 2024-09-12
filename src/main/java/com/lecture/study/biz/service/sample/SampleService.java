package com.lecture.study.biz.service.sample;

import com.lecture.study.biz.service.sample.vo.SampleVO;

/**
 * 샘플 서비스 인터페이스
 */
public interface SampleService {

    /**
     * DB 연결 확인 샘플 서비스 메소드
     * @return
     * @throws Exception
     */
    SampleVO dbConeectCheck() throws Exception;
}
