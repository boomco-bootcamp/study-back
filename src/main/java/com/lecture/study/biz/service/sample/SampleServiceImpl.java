package com.lecture.study.biz.service.sample;

import com.lecture.study.biz.service.sample.repo.SampleRepository;
import com.lecture.study.biz.service.sample.vo.SampleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service("SampleService")
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;

    public SampleServiceImpl(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    /**
     * DB 연결 확인 샘플 서비스 임플리먼트 메소드
     * @return
     * @throws Exception
     */
    public SampleVO dbConeectCheck() throws Exception {
        try {

            SampleVO result = sampleRepository.dbConeectCheck();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



}
