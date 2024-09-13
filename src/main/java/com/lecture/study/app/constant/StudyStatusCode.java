package com.lecture.study.app.constant;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum StudyStatusCode {

    /** 스터디 상태 */
    STUDY_NOT_STARTED ("ready", "모집중 "),
    STUDY_RUNNING ("proceeding", "진행중"),
    STUDY_FINISHED ("closure", "종료"),

    /** 스터디 커뮤니티 상태 */
    COMMUNITY_NOTICE("notice", "공지"),
    COMMUNITY_INQUIRY("inquiry", "문의"),
    COMMUNITY_JOINMESSAGE("joinMessage", "가입인사"),
    COMMUNITY_QNA("qna", "질의응답"),
    COMMUNITY_FREE("free", "자유글"),

    /** 스터디 조회 필터 */
    ORDER_DESC("desc", "최신순"),
    ORDER_VIEW("view", "조회수"),
    ORDER_REPLY("reply", "댓글 많은 순");

    private final String code;
    private final String message;

    StudyStatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public static List<Map<String, String>> getCodes(String type) {

        ArrayList<Map<String, String>> list = new ArrayList<>();

        if(StringUtils.isBlank(type)) {
            return list;
        }

        else if(type.equals("study")) {
            Map<String, String> map = new HashMap<>();
            map.put("code", STUDY_NOT_STARTED.getCode());
            map.put("message", STUDY_NOT_STARTED.getMessage());
            list.add(map);

            map = new HashMap<>();
            map.put("code", STUDY_RUNNING.getCode());
            map.put("message", STUDY_RUNNING.getMessage());
            list.add(map);

            map = new HashMap<>();
            map.put("code", STUDY_FINISHED.getCode());
            map.put("message", STUDY_FINISHED.getMessage());
            list.add(map);
        }

        else if (type.equals("notice")) {
            Map<String, String> map = new HashMap<>();
            map.put("code", COMMUNITY_NOTICE.getCode());
            map.put("message", COMMUNITY_NOTICE.getMessage());
            list.add(map);

            map = new HashMap<>();
            map.put("code", COMMUNITY_INQUIRY.getCode());
            map.put("message", COMMUNITY_INQUIRY.getMessage());
            list.add(map);

            map = new HashMap<>();
            map.put("code", COMMUNITY_JOINMESSAGE.getCode());
            map.put("message", COMMUNITY_JOINMESSAGE.getMessage());
            list.add(map);

            map = new HashMap<>();
            map.put("code", COMMUNITY_QNA.getCode());
            map.put("message", COMMUNITY_QNA.getMessage());
            list.add(map);

            map = new HashMap<>();
            map.put("code", COMMUNITY_FREE.getCode());
            map.put("message", COMMUNITY_FREE.getMessage());
            list.add(map);
        }

        else if (type.equals("order")) {
            Map<String, String> map = new HashMap<>();
            map.put("code", ORDER_DESC.getCode());
            map.put("message", ORDER_DESC.getMessage());
            list.add(map);

            map = new HashMap<>();
            map.put("code", ORDER_VIEW.getCode());
            map.put("message", ORDER_VIEW.getMessage());
            list.add(map);

            map = new HashMap<>();
            map.put("code", ORDER_REPLY.getCode());
            map.put("message", ORDER_REPLY.getMessage());
            list.add(map);
        }

        return list;
    }
}
