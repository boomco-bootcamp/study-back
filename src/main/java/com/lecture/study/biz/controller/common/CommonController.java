package com.lecture.study.biz.controller.common;

import com.lecture.study.app.constant.StudyStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 스티디 공통 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/common", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CommonController {

    /**
     * 스테이터스 코드 조회
     * @param type
     * @return
     */
    @GetMapping("/code/status")
    public ResponseEntity getStatusCode(@RequestParam("type") String type) {
        try {
            List<Map<String, String>> mapList = StudyStatusCode.getCodes(type);
            return ResponseEntity.ok().body(mapList);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
