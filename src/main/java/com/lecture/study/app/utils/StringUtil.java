package com.lecture.study.app.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 인수를 문자열로 받는 문자열 유틸
 */
public class StringUtil {

    public static Timestamp convertStringToTimestamp(String dateString) {
        // 날짜 형식 정의
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            // 문자열을 Date로 변환
            Date date = dateFormat.parse(dateString);
            // Date를 Timestamp로 변환
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // 변환 실패 시 null 반환
        }
    }
}
