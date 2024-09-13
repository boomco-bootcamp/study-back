-- 스터디 유저 계정 정보
CREATE TABLE study.STDY_USER_INFO_M (
                                            USER_ID       varchar(50)   NOT NULL, -- 유저 ID
                                            USER_PSWD     varchar(1000) NULL,     -- 유저 패스워드
                                            USER_NM       varchar(50)   NULL,     -- 유저명
                                            USER_EML      varchar(255)  NULL,     -- 유저 이메일 주소
                                            USER_TEL      varchar(30)   NULL,     -- 유저 전화번호
                                            USER_SNS_ID   varchar(300)  NULL,     -- 유저 SNS_ID
                                            USER_SNS_TYPE varchar(50)   NULL,     -- 유저 SNS_TYPE
                                            SYS_ADM_YN    varchar(1)    NULL,     -- 시스템 관리자 여부
                                            DEL_YN        varchar(1)    NULL,     -- 삭제 여부
                                            RGSN_USER_ID  varchar(50)   NULL,     -- 등록 사용자
                                            RGSN_TS       TIMESTAMP     NULL,  -- 등록 일시
                                            AMNN_USER_ID  varchar(50)   NULL,     -- 수정 사용자
                                            AMNN_TS       TIMESTAMP     NULL   -- 수정 일시
);

-- 스터디 유저 계정 정보
ALTER TABLE study.STDY_USER_INFO_M
    ADD CONSTRAINT PK_STDY_USER_INFO_M -- 스터디 유저 계정 정보 기본키
        PRIMARY KEY (
                     USER_ID -- 유저 ID
            );

COMMENT ON TABLE  study.STDY_USER_INFO_M IS '스터디 유저 계정 정보';
COMMENT ON COLUMN study.STDY_USER_INFO_M.USER_PSWD IS '유저 ID';
COMMENT ON COLUMN study.STDY_USER_INFO_M.USER_NM IS '유저 패스워드';
COMMENT ON COLUMN study.STDY_USER_INFO_M.USER_EML IS '유저명';
COMMENT ON COLUMN study.STDY_USER_INFO_M.USER_TEL IS '유저 이메일 주소';
COMMENT ON COLUMN study.STDY_USER_INFO_M.USER_SNS_ID IS '유저 전화번호';
COMMENT ON COLUMN study.STDY_USER_INFO_M.USER_SNS_TYPE IS '유저 SNS_ID';
COMMENT ON COLUMN study.STDY_USER_INFO_M.SYS_ADM_YN IS '유저 SNS_TYPE';
COMMENT ON COLUMN study.STDY_USER_INFO_M.DEL_YN IS '시스템 관리자 여부';
COMMENT ON COLUMN study.STDY_USER_INFO_M.RGSN_USER_ID IS '삭제 여부';
COMMENT ON COLUMN study.STDY_USER_INFO_M.RGSN_TS IS '등록 사용자';
COMMENT ON COLUMN study.STDY_USER_INFO_M.AMNN_USER_ID IS '등록 일시';
COMMENT ON COLUMN study.STDY_USER_INFO_M.AMNN_TS IS '수정 사용자';



-- 스터디 정보
CREATE TABLE study.STDY_INFO_M  (
                                        STDY_ID      varchar(100) NOT NULL, -- 스터디 ID
                                        STDY_NM      varchar(100) NULL,     -- 스터티 명
                                        STDY_CON     TEXT         NULL,  -- 스터디 내용
                                        STDY_ST_DT   TIMESTAMP    NULL,  -- 스터디 시작일
                                        STDY_EN_DT   TIMESTAMP    NULL,  -- 스터디 종료일
                                        STDY_ST      varchar(20)   NULL,     -- 스터디 상태
                                        STDY_CAT_ID  varchar(100) NULL,     -- 스터디 카테고리 ID
                                        DEL_YN       varchar(1)   NULL,     -- 삭제 여부
                                        RGSN_USER_ID varchar(50)  NULL,     -- 등록 사용자
                                        RGSN_TS      TIMESTAMP    NULL,  -- 등록 일시
                                        AMNN_USER_ID varchar(50)  NULL,     -- 수정 사용자
                                        AMNN_TS      TIMESTAMP    NULL   -- 수정 일시
);

-- 스터디 정보
ALTER TABLE study.STDY_INFO_M
    ADD CONSTRAINT PK_STDY_INFO_M  -- 스터디 정보 기본키
        PRIMARY KEY (
                     STDY_ID -- 스터디 ID
            );


COMMENT ON TABLE  study.STDY_INFO_M IS '스터디 정보';
COMMENT ON COLUMN study.STDY_INFO_M.STDY_ID IS '스터디 ID';
COMMENT ON COLUMN study.STDY_INFO_M.STDY_NM IS '스터티 명';
COMMENT ON COLUMN study.STDY_INFO_M.STDY_CON IS '스터디 내용';
COMMENT ON COLUMN study.STDY_INFO_M.STDY_ST_DT IS '스터디 시작일';
COMMENT ON COLUMN study.STDY_INFO_M.STDY_EN_DT IS '스터디 종료일';
COMMENT ON COLUMN study.STDY_INFO_M.STDY_ST IS '스터디 상태';
COMMENT ON COLUMN study.STDY_INFO_M.STDY_CAT_ID IS '스터디 카테고리 ID';
COMMENT ON COLUMN study.STDY_INFO_M.DEL_YN IS '삭제 여부';
COMMENT ON COLUMN study.STDY_INFO_M.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_INFO_M.RGSN_TS IS '등록 일시';
COMMENT ON COLUMN study.STDY_INFO_M.AMNN_USER_ID IS '수정 사용자';
COMMENT ON COLUMN study.STDY_INFO_M.AMNN_TS IS '수정 일시';






-- 스터디 참여인원
CREATE TABLE study.STDY_MEMBER_R  (
                                          STDY_MEMBER_ID varchar(100) NOT NULL, -- 스터디 참여인원 ID
                                          STDY_ID        varchar(100) NOT NULL, -- 스터디 ID
                                          USER_ID        varchar(50)  NOT NULL, -- 유저 ID
                                          STDY_MEMBER_YN varchar(1)   NULL,     -- 스터디 참여 상태
                                          DEL_YN         varchar(1)   NULL,     -- 삭제 여부
                                          RGSN_USER_ID   varchar(50)  NULL,     -- 등록 사용자
                                          RGSN_TS        TIMESTAMP    NULL,     -- 등록 일시
                                          AMNN_USER_ID   varchar(50)  NULL,     -- 수정 사용자
                                          AMNN_TS        TIMESTAMP    NULL      -- 수정 일시
);

-- 스터디 참여인원
ALTER TABLE study.STDY_MEMBER_R
    ADD CONSTRAINT PK_STDY_MEMBER_R  -- 스터디 참여인원 기본키
        PRIMARY KEY (
                     STDY_MEMBER_ID -- 스터디 참여인원 ID
            );



COMMENT ON TABLE  study.STDY_MEMBER_R IS '스터디 참여인원';
COMMENT ON COLUMN study.STDY_MEMBER_R.STDY_MEMBER_ID IS '스터디 참여인원 ID';
COMMENT ON COLUMN study.STDY_MEMBER_R.STDY_ID IS '스터디 ID';
COMMENT ON COLUMN study.STDY_MEMBER_R.USER_ID IS '유저 ID';
COMMENT ON COLUMN study.STDY_MEMBER_R.STDY_MEMBER_YN IS '스터디 참여 상태';
COMMENT ON COLUMN study.STDY_MEMBER_R.DEL_YN IS '삭제 여부';
COMMENT ON COLUMN study.STDY_MEMBER_R.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_MEMBER_R.RGSN_TS IS '등록 일시';
COMMENT ON COLUMN study.STDY_MEMBER_R.AMNN_USER_ID IS '수정 사용자';
COMMENT ON COLUMN study.STDY_MEMBER_R.AMNN_TS IS '수정 일시';




-- 스터디 태그 목록
CREATE TABLE study.STDY_TAG_L  (
                                       STDY_TAG_ID  varchar(100) NOT NULL, -- 스터디 태그 ID
                                       STDY_ID      varchar(100) NOT NULL, -- 스터디 ID
                                       STDY_TAG_CON varchar(100) NULL,     -- 스터티 태그 내용
                                       DEL_YN       varchar(1)   NULL,     -- 삭제 여부
                                       RGSN_USER_ID varchar(50)  NULL,     -- 등록 사용자
                                       RGSN_TS      TIMESTAMP    NULL,     -- 등록 일시
                                       AMNN_USER_ID varchar(50)  NULL,     -- 수정 사용자
                                       AMNN_TS      TIMESTAMP    NULL      -- 수정 일시
);

-- 스터디 태그 목록
ALTER TABLE study.STDY_TAG_L
    ADD CONSTRAINT PK_STDY_TAG_L  -- 스터디 태그 목록 기본키
        PRIMARY KEY (
                     STDY_TAG_ID -- 스터디 태그 ID
            );



COMMENT ON TABLE  study.STDY_TAG_L IS '스터디 태그 목록';
COMMENT ON COLUMN study.STDY_TAG_L.STDY_TAG_ID IS '스터디 태그 ID';
COMMENT ON COLUMN study.STDY_TAG_L.STDY_ID IS '스터디 ID';
COMMENT ON COLUMN study.STDY_TAG_L.STDY_TAG_CON IS '스터티 태그 내용';
COMMENT ON COLUMN study.STDY_TAG_L.DEL_YN IS '삭제 여부';
COMMENT ON COLUMN study.STDY_TAG_L.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_TAG_L.RGSN_TS IS '등록 일시';
COMMENT ON COLUMN study.STDY_TAG_L.AMNN_USER_ID IS '수정 사용자';
COMMENT ON COLUMN study.STDY_TAG_L.AMNN_TS IS '수정 일시';





-- 스터디 카테고리 정보
CREATE TABLE study.STDY_CAT_INFO_M  (
                                            STDY_CAT_ID  varchar(100) NOT NULL, -- 스터디 카테고리 ID
                                            STDY_CAT_NM  varchar(50)  NULL,     -- 스터디 카테고리 명
                                            DEL_YN       varchar(1)   NULL,     -- 삭제 여부
                                            RGSN_USER_ID varchar(50)  NULL,     -- 등록 사용자
                                            RGSN_TS      TIMESTAMP    NULL,     -- 등록 일시
                                            AMNN_USER_ID varchar(50)  NULL,     -- 수정 사용자
                                            AMNN_TS      TIMESTAMP    NULL      -- 수정 일시
);

-- 스터디 카테고리 정보
ALTER TABLE study.STDY_CAT_INFO_M
    ADD CONSTRAINT PK_STDY_CAT_INFO_M  -- 스터디 카테고리 정보 기본키
        PRIMARY KEY (
                     STDY_CAT_ID -- 스터디 카테고리 ID
            );



COMMENT ON TABLE  study.STDY_CAT_INFO_M IS '스터디 카테고리 정보';
COMMENT ON COLUMN study.STDY_CAT_INFO_M.STDY_CAT_ID IS '스터디 카테고리 ID';
COMMENT ON COLUMN study.STDY_CAT_INFO_M.STDY_CAT_NM IS '스터디 카테고리 명';
COMMENT ON COLUMN study.STDY_CAT_INFO_M.DEL_YN IS '삭제 여부';
COMMENT ON COLUMN study.STDY_CAT_INFO_M.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_CAT_INFO_M.RGSN_TS IS '등록 일시';
COMMENT ON COLUMN study.STDY_CAT_INFO_M.AMNN_USER_ID IS '수정 사용자';
COMMENT ON COLUMN study.STDY_CAT_INFO_M.AMNN_TS IS '수정 일시';





-- 스터디 댓글 목록
CREATE TABLE study.STDY_COMMENT_L  (
                                           STDY_COMMENT_ID        varchar(100) NOT NULL, -- 스터디 댓글 ID
                                           STDY_ID                varchar(100) NOT NULL, -- 스터디 ID
                                           STDY_COMMENT_CON       varchar(500) NULL,     -- 스터티 댓글 내용
                                           STDY_PARENT_COMMENT_ID varchar(100) NULL,     -- 스터디 부모 댓글 ID
                                           DEL_YN                 varchar(1)   NULL,     -- 삭제 여부
                                           RGSN_USER_ID           varchar(50)  NULL,     -- 등록 사용자
                                           RGSN_TS                TIMESTAMP    NULL,     -- 등록 일시
                                           AMNN_USER_ID           varchar(50)  NULL,     -- 수정 사용자
                                           AMNN_TS                TIMESTAMP    NULL      -- 수정 일시
);

-- 스터디 댓글 목록
ALTER TABLE study.STDY_COMMENT_L
    ADD CONSTRAINT PK_STDY_COMMENT_L  -- 스터디 댓글 목록 기본키
        PRIMARY KEY (
                     STDY_COMMENT_ID -- 스터디 댓글 ID
            );



COMMENT ON TABLE  study.STDY_COMMENT_L IS '스터디 댓글 목록';
COMMENT ON COLUMN study.STDY_COMMENT_L.STDY_COMMENT_ID IS '스터디 댓글 ID';
COMMENT ON COLUMN study.STDY_COMMENT_L.STDY_ID IS '스터디 ID';
COMMENT ON COLUMN study.STDY_COMMENT_L.STDY_COMMENT_CON IS '스터티 댓글 내용';
COMMENT ON COLUMN study.STDY_COMMENT_L.STDY_PARENT_COMMENT_ID IS '스터디 부모 댓글 ID';
COMMENT ON COLUMN study.STDY_COMMENT_L.DEL_YN IS '삭제 여부';
COMMENT ON COLUMN study.STDY_COMMENT_L.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_COMMENT_L.RGSN_TS IS '등록 일시';
COMMENT ON COLUMN study.STDY_COMMENT_L.AMNN_USER_ID IS '수정 사용자';
COMMENT ON COLUMN study.STDY_COMMENT_L.AMNN_TS IS '수정 일시';






-- 스터디 관심 목록
CREATE TABLE study.STDY_LIKE_L  (
                                        USER_ID      varchar(50)  NOT NULL, -- 유저 ID
                                        STDY_ID      varchar(100) NOT NULL, -- 스터디 ID
                                        RGSN_USER_ID varchar(50)  NULL,     -- 등록 사용자
                                        RGSN_TS      TIMESTAMP    NULL      -- 등록 일시
);

-- 스터디 관심 목록
ALTER TABLE study.STDY_LIKE_L
    ADD CONSTRAINT PK_STDY_LIKE_L  -- 스터디 관심 목록 기본키
        PRIMARY KEY (
                     USER_ID, -- 유저 ID
                     STDY_ID  -- 스터디 ID
            );



COMMENT ON TABLE  study.STDY_LIKE_L IS '스터디 관심 목록';
COMMENT ON COLUMN study.STDY_LIKE_L.USER_ID IS '유저 ID';
COMMENT ON COLUMN study.STDY_LIKE_L.STDY_ID IS '스터디 ID';
COMMENT ON COLUMN study.STDY_LIKE_L.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_LIKE_L.RGSN_TS IS '등록 일시';





-- 스터디 커뮤니티 목록
CREATE TABLE study.STDY_COM_L  (
                                       STDY_COMT_ID   varchar(100) NOT NULL, -- 스터디 커뮤니티 ID
                                       STDY_ID        varchar(100) NOT NULL, -- 스터디 ID
                                       STDY_COM_TITLE varchar(500) NULL,     -- 스터티 커뮤니티 제목
                                       STDY_COM_CON   varchar(100) NULL,     -- 스터디 커뮤니티 내용
                                       STDY_COM_VIEWS NUMERIC(15)  NULL,     -- 스터디 커뮤니티 조회수
                                       STDY_COM_ST    varchar(20)   NULL,     -- 스터디 커뮤니티 상태
                                       DEL_YN         varchar(1)   NULL,     -- 삭제 여부
                                       RGSN_USER_ID   varchar(50)  NULL,     -- 등록 사용자
                                       RGSN_TS        TIMESTAMP    NULL,     -- 등록 일시
                                       AMNN_USER_ID   varchar(50)  NULL,     -- 수정 사용자
                                       AMNN_TS        TIMESTAMP    NULL      -- 수정 일시
);

-- 스터디 커뮤니티 목록
ALTER TABLE study.STDY_COM_L
    ADD CONSTRAINT PK_STDY_COM_L  -- 스터디 커뮤니티 목록 기본키
        PRIMARY KEY (
                     STDY_COMT_ID -- 스터디 커뮤니티 ID
            );



COMMENT ON TABLE  study.STDY_COM_L IS '스터디 커뮤니티 목록';
COMMENT ON COLUMN study.STDY_COM_L.STDY_COMT_ID IS '스터디 커뮤니티 ID';
COMMENT ON COLUMN study.STDY_COM_L.STDY_ID IS '스터디 ID';
COMMENT ON COLUMN study.STDY_COM_L.STDY_COM_TITLE IS '스터티 커뮤니티 제목';
COMMENT ON COLUMN study.STDY_COM_L.STDY_COM_CON IS '스터디 커뮤니티 내용';
COMMENT ON COLUMN study.STDY_COM_L.STDY_COM_VIEWS IS '스터디 커뮤니티 조회수';
COMMENT ON COLUMN study.STDY_COM_L.STDY_COM_ST IS '스터디 커뮤니티 상태';
COMMENT ON COLUMN study.STDY_COM_L.DEL_YN IS '삭제 여부';
COMMENT ON COLUMN study.STDY_COM_L.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_COM_L.RGSN_TS IS '등록 일시';
COMMENT ON COLUMN study.STDY_COM_L.AMNN_USER_ID IS '수정 사용자';
COMMENT ON COLUMN study.STDY_COM_L.AMNN_TS IS '수정 일시';





-- 스터디 커뮤니티 첨부파일 목록
CREATE TABLE study.STDY_COM_FILE_L (
                                           STDY_COMT_ID varchar(100) NOT NULL, -- 스터디 커뮤니티 ID
                                           FILE_ID      varchar(100) NOT NULL, -- 파일 ID
                                           DEL_YN       varchar(1)   NULL,     -- 삭제 여부
                                           RGSN_USER_ID varchar(50)  NULL,     -- 등록 사용자
                                           RGSN_TS      TIMESTAMP    NULL,     -- 등록 일시
                                           AMNN_USER_ID varchar(50)  NULL,     -- 수정 사용자
                                           AMNN_TS      TIMESTAMP    NULL      -- 수정 일시
);

-- 스터디 커뮤니티 첨부파일 목록
ALTER TABLE study.STDY_COM_FILE_L
    ADD CONSTRAINT PK_STDY_COM_FILE_L -- 스터디 커뮤니티 첨부파일 목록 기본키
        PRIMARY KEY (
                     STDY_COMT_ID, -- 스터디 커뮤니티 ID
                     FILE_ID       -- 파일 ID
            );




COMMENT ON TABLE  study.STDY_COM_FILE_L IS '스터디 커뮤니티 첨부파일 목록';
COMMENT ON COLUMN study.STDY_COM_FILE_L.STDY_COMT_ID IS '스터디 커뮤니티 ID';
COMMENT ON COLUMN study.STDY_COM_FILE_L.FILE_ID IS '파일 ID';
COMMENT ON COLUMN study.STDY_COM_FILE_L.DEL_YN IS '삭제 여부';
COMMENT ON COLUMN study.STDY_COM_FILE_L.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_COM_FILE_L.RGSN_TS IS '등록 일시';
COMMENT ON COLUMN study.STDY_COM_FILE_L.AMNN_USER_ID IS '수정 사용자';
COMMENT ON COLUMN study.STDY_COM_FILE_L.AMNN_TS IS '수정 일시';





-- 첨부파일 정보
CREATE TABLE study.FILE_ATCH_M (
                                       FILE_ID      varchar(100)  NOT NULL, -- 파일 ID
                                       FILE_NM      varchar(1000) NULL,     -- 파일 명
                                       FILE_PATH    varchar(4000) NULL,     -- 파일 경로
                                       FILE_ENTS    varchar(10)   NULL,     -- 파일 확장자
                                       FILE_PTRN    varchar(100)  NULL,     -- 파일 유형
                                       FILE_SIZE    NUMERIC(15)   NULL,     -- 파일 크기
                                       DEL_YN       varchar(1)    NULL,     -- 삭제 여부
                                       RGSN_USER_ID varchar(50)   NULL,     -- 등록 사용자
                                       RGSN_TS      TIMESTAMP     NULL,     -- 등록 일시
                                       AMNN_USER_ID varchar(50)   NULL,     -- 수정 사용자
                                       AMNN_TS      TIMESTAMP     NULL      -- 수정 일시
);

-- 첨부파일 정보
ALTER TABLE study.FILE_ATCH_M
    ADD CONSTRAINT PK_FILE_ATCH_M -- 첨부파일 정보 기본키
        PRIMARY KEY (
                     FILE_ID -- 파일 ID
            );



COMMENT ON TABLE  study.FILE_ATCH_M IS '첨부파일 정보';
COMMENT ON COLUMN study.FILE_ATCH_M.FILE_ID IS '파일 ID';
COMMENT ON COLUMN study.FILE_ATCH_M.FILE_NM IS '파일 명';
COMMENT ON COLUMN study.FILE_ATCH_M.FILE_PATH IS '파일 경로';
COMMENT ON COLUMN study.FILE_ATCH_M.FILE_ENTS IS '파일 확장자';
COMMENT ON COLUMN study.FILE_ATCH_M.FILE_PTRN IS '파일 유형';
COMMENT ON COLUMN study.FILE_ATCH_M.FILE_SIZE IS '파일 크기';
COMMENT ON COLUMN study.FILE_ATCH_M.DEL_YN IS '삭제 여부';
COMMENT ON COLUMN study.FILE_ATCH_M.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.FILE_ATCH_M.RGSN_TS IS '등록 일시';
COMMENT ON COLUMN study.FILE_ATCH_M.AMNN_USER_ID IS '수정 사용자';
COMMENT ON COLUMN study.FILE_ATCH_M.AMNN_TS IS '수정 일시';





-- 스터디 알림 목록
CREATE TABLE study.STDY_ALARM_L (
                                        STDY_ALARM_ID varchar(100) NOT NULL, -- 스터디 알림 목록 ID
                                        USER_ID       varchar(50)  NOT NULL, -- 유저 ID
                                        STDY_ID       varchar(100) NOT NULL, -- 스터디 ID
                                        STDY_ALARM_YN varchar(1)   NOT NULL, -- 스터디 알림 타입
                                        READ_YN       varchar(1)   NULL,     -- 읽기 여부
                                        DEL_YN        varchar(1)   NULL,     -- 삭제 여부
                                        RGSN_USER_ID  varchar(50)  NULL,     -- 등록 사용자
                                        RGSN_TS       TIMESTAMP    NULL,     -- 등록 일시
                                        AMNN_USER_ID  varchar(50)  NULL,     -- 수정 사용자
                                        AMNN_TS       TIMESTAMP    NULL      -- 수정 일시
);

-- 스터디 알림 목록
ALTER TABLE study.STDY_ALARM_L
    ADD CONSTRAINT PK_STDY_ALARM_L -- 스터디 알림 목록 기본키
        PRIMARY KEY (
                     STDY_ALARM_ID -- 스터디 알림 목록 ID
            );



COMMENT ON TABLE  study.STDY_ALARM_L IS '스터디 알림 목록';
COMMENT ON COLUMN study.STDY_ALARM_L.STDY_ALARM_ID IS '스터디 알림 목록 ID';
COMMENT ON COLUMN study.STDY_ALARM_L.USER_ID IS '유저 ID';
COMMENT ON COLUMN study.STDY_ALARM_L.STDY_ID IS '스터디 ID';
COMMENT ON COLUMN study.STDY_ALARM_L.STDY_ALARM_YN IS '스터디 알림 타입';
COMMENT ON COLUMN study.STDY_ALARM_L.READ_YN IS '읽기 여부';
COMMENT ON COLUMN study.STDY_ALARM_L.DEL_YN IS '삭제 여부';
COMMENT ON COLUMN study.STDY_ALARM_L.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_ALARM_L.RGSN_TS IS '등록 일시';
COMMENT ON COLUMN study.STDY_ALARM_L.AMNN_USER_ID IS '수정 사용자';
COMMENT ON COLUMN study.STDY_ALARM_L.AMNN_TS IS '수정 일시';





-- 스터디 관심 태그 목록
CREATE TABLE study.STDY_LIKE_TAG_L  (
                                            STDY_LIKE_TAG_ID  varchar(100) NOT NULL, -- 스터디 관심 태그 목록 ID
                                            USER_ID           varchar(50)  NOT NULL, -- 유저 ID
                                            STDY_LIKE_TAG_CON varchar(100) NOT NULL, -- 관심 태그 내용
                                            RGSN_USER_ID      varchar(50)  NULL,     -- 등록 사용자
                                            RGSN_TS           TIMESTAMP    NULL      -- 등록 일시
);

-- 스터디 관심 태그 목록
ALTER TABLE study.STDY_LIKE_TAG_L
    ADD CONSTRAINT PK_STDY_LIKE_TAG_L  -- 스터디 관심 태그 목록 기본키
        PRIMARY KEY (
                     STDY_LIKE_TAG_ID -- 스터디 관심 태그 목록 ID
            );




COMMENT ON TABLE  study.STDY_LIKE_TAG_L IS '스터디 관심 태그 목록';
COMMENT ON COLUMN study.STDY_LIKE_TAG_L.STDY_LIKE_TAG_ID IS '스터디 관심 태그 목록 ID';
COMMENT ON COLUMN study.STDY_LIKE_TAG_L.USER_ID IS '유저 ID';
COMMENT ON COLUMN study.STDY_LIKE_TAG_L.STDY_LIKE_TAG_CON IS '관심 태그 내용';
COMMENT ON COLUMN study.STDY_LIKE_TAG_L.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_LIKE_TAG_L.RGSN_TS IS '등록 일시';






-- 스터디 관심 카테고리 목록
CREATE TABLE study.STDY_LIKE_CAT_R (
                                           USER_ID      varchar(50)  NOT NULL, -- 유저 ID
                                           STDY_CAT_ID  varchar(100) NOT NULL, -- 스터디 카테고리 ID
                                           RGSN_USER_ID varchar(50)  NULL,     -- 등록 사용자
                                           RGSN_TS      TIMESTAMP    NULL      -- 등록 일시
);

-- 스터디 관심 카테고리 목록
ALTER TABLE study.STDY_LIKE_CAT_R
    ADD CONSTRAINT PK_STDY_LIKE_CAT_R -- 스터디 관심 카테고리 목록 기본키
        PRIMARY KEY (
                     USER_ID,     -- 유저 ID
                     STDY_CAT_ID  -- 스터디 카테고리 ID
            );




COMMENT ON TABLE  study.STDY_LIKE_CAT_R IS '스터디 관심 카테고리 목록';
COMMENT ON COLUMN study.STDY_LIKE_CAT_R.USER_ID IS '유저 ID';
COMMENT ON COLUMN study.STDY_LIKE_CAT_R.STDY_CAT_ID IS '스터디 카테고리 ID';
COMMENT ON COLUMN study.STDY_LIKE_CAT_R.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_LIKE_CAT_R.RGSN_TS IS '등록 일시';






-- 스터디 커뮤니티 방문자 목록
CREATE TABLE study.STDY_COM_VIEWS_L   (
                                              STDY_COM_VIEWS_ID varchar(100) NOT NULL, -- 스터디 커뮤니티 방문자 ID
                                              STDY_COMT_ID      varchar(100) NOT NULL, -- 스터디 커뮤니티 ID
                                              RGSN_USER_ID      varchar(50)  NULL,     -- 등록 사용자
                                              RGSN_TS           TIMESTAMP    NULL      -- 등록 일시
);

-- 스터디 커뮤니티 방문자 목록
ALTER TABLE study.STDY_COM_VIEWS_L
    ADD CONSTRAINT PK_STDY_COM_VIEWS_L   -- 스터디 커뮤니티 방문자 목록 기본키
        PRIMARY KEY (
                     STDY_COM_VIEWS_ID -- 스터디 커뮤니티 방문자 ID
            );




COMMENT ON TABLE  study.STDY_COM_VIEWS_L IS '스터디 커뮤니티 방문자 목록';
COMMENT ON COLUMN study.STDY_COM_VIEWS_L.STDY_COM_VIEWS_ID IS '스터디 커뮤니티 방문자 ID';
COMMENT ON COLUMN study.STDY_COM_VIEWS_L.STDY_COMT_ID IS '스터디 커뮤니티 ID';
COMMENT ON COLUMN study.STDY_COM_VIEWS_L.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_COM_VIEWS_L.RGSN_TS IS '등록 일시';



-- 스터디 커뮤니티 방문자 목록
CREATE TABLE study.STDY_VIEWS_L   (
                                          STDY_VIEWS_ID varchar(100) NOT NULL, -- 스터디 커뮤니티 방문자 ID
                                          STDY_ID      varchar(100) NOT NULL, -- 스터디 커뮤니티 ID
                                          RGSN_USER_ID      varchar(50)  NULL,     -- 등록 사용자
                                          RGSN_TS           TIMESTAMP    NULL      -- 등록 일시
);

-- 스터디 커뮤니티 방문자 목록
ALTER TABLE study.STDY_VIEWS_L
    ADD CONSTRAINT PK_STDY_VIEWS_L   -- 스터디 커뮤니티 방문자 목록 기본키
        PRIMARY KEY (
                     STDY_COM_ID -- 스터디 커뮤니티 방문자 ID
            );




COMMENT ON TABLE  study.STDY_VIEWS_L IS '스터디 방문자 목록';
COMMENT ON COLUMN study.STDY_VIEWS_L.STDY_VIEWS_ID IS '스터디 방문자 ID';
COMMENT ON COLUMN study.STDY_VIEWS_L.STDY_ID IS '스터디 ID';
COMMENT ON COLUMN study.STDY_VIEWS_L.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_VIEWS_L.RGSN_TS IS '등록 일시';



-- 스터디 커뮤니티 댓글 목록
CREATE TABLE MY_SCHEMA.STDY_COM_COMMENT_L    (
                                                 STDY_COM_COMMENT_ID             VARCHAR(100) NOT NULL, -- 스터디 커뮤니티 댓글 ID
                                                 STDY_COM_ID                     VARCHAR(100) NOT NULL, -- 스터디 커뮤니티 ID
                                                 STDY_COM_COMMENT_CON            VARCHAR(500) NULL,     -- 스터티 커뮤니티 댓글 내용
                                                 STDY_PARENT_COM_COMMENT_ID      VARCHAR(100) NULL,     -- 스터디 커뮤니티 부모 댓글 ID
                                                 DEL_YN                          VARCHAR(1)   NULL,     -- 삭제 여부
                                                 RGSN_USER_ID                    VARCHAR(50)  NULL,     -- 등록 사용자
                                                 RGSN_TS                         TIMESTAMP    NULL,     -- 등록 일시
                                                 AMNN_USER_ID                    VARCHAR(50)  NULL,     -- 수정 사용자
                                                 AMNN_TS                         TIMESTAMP    NULL      -- 수정 일시
);

-- 스터디 커뮤니티 댓글 목록
ALTER TABLE MY_SCHEMA.STDY_COM_COMMENT_L
    ADD CONSTRAINT PK_STDY_COM_COMMENT_L    -- 스터디 커뮤니티 댓글 목록 기본키
        PRIMARY KEY (
                     STDY_COM_COMMENT_ID -- 스터디 커뮤니티 댓글 ID
            );

COMMENT ON TABLE  study.STDY_COM_COMMENT_L IS '스터디 커뮤니티 댓글 목록';
COMMENT ON COLUMN study.STDY_COM_COMMENT_L.STDY_COM_COMMENT_ID IS '스터디 커뮤니티 댓글 ID';
COMMENT ON COLUMN study.STDY_COM_COMMENT_L.STDY_COM_ID IS '스터디 커뮤니티 ID';
COMMENT ON COLUMN study.STDY_COM_COMMENT_L.STDY_COM_COMMENT_CON IS '스터티 커뮤니티 댓글 내용';
COMMENT ON COLUMN study.STDY_COM_COMMENT_L.STDY_PARENT_COM_COMMENT_ID IS '스터디 커뮤니티 부모 댓글 ID';
COMMENT ON COLUMN study.STDY_COM_COMMENT_L.DEL_YN IS '삭제 여부';
COMMENT ON COLUMN study.STDY_COM_COMMENT_L.RGSN_USER_ID IS '등록 사용자';
COMMENT ON COLUMN study.STDY_COM_COMMENT_L.RGSN_TS IS '등록 일시';
COMMENT ON COLUMN study.STDY_COM_COMMENT_L.AMNN_USER_ID IS '수정 사용자';
COMMENT ON COLUMN study.STDY_COM_COMMENT_L.AMNN_TS IS '수정 일시';