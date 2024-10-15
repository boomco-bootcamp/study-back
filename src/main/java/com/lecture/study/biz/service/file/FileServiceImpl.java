package com.lecture.study.biz.service.file;

import com.lecture.study.biz.service.file.repo.FileRepository;
import com.lecture.study.biz.service.file.vo.FileSaveReqVO;
import com.lecture.study.biz.service.file.vo.FileVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Getter
    private final String fileUploadPath;

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository, @Value("${file.path}") String filePath) {
        this.fileRepository = fileRepository;

        // properties file upload path는 final로 두고 초기화 시점에 unix 체크를 한다.
        boolean isUnix = File.separator.equals("/");
        // file upload path 초기화
        this.fileUploadPath = isUnix ? filePath.replace("\\", File.separator) : filePath.replace("/", File.separator);

        log.info("filePath : {}", filePath);
    }

    /**
     * 파일 정보 조회
     * @param fileId
     * @return
     * @throws Exception
     */
    @Override
    public FileVO searchFileInfo(String fileId) throws Exception {
        try {
            return fileRepository.selectFileInfo(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 파일 업로드
     * @param reqVO
     * @return
     * @throws Exception
     */
    @Override
    public String uploadFile(FileSaveReqVO reqVO) throws Exception {
        try {
            /*
             * 파일 물리 업로드
             */
            MultipartFile multipartFile = reqVO.getFile();
            // 파일정보 추출
            String originalFileName = multipartFile.getOriginalFilename();
            String fileName = originalFileName.substring(originalFileName.lastIndexOf("\\") + 1); // IE, Edge 브라우저 환경에서는 전체 경로가 들어옴

            // mac os 유니코드 정규화 차이에 따른 한글깨짐 수정
            fileName = Normalizer.normalize(fileName, Normalizer.Form.NFC);

            // 확장자 | mime type
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
            String mime = multipartFile.getContentType();

            fileName = fileName.substring(0, fileName.lastIndexOf("."));

            // 위의 FilenameUtils에서 문제가 있을 경우 대체
            fileName = fileName.replace("/", "")
                    .replace("\\\\", "")
                    .replace(".", "")
                    .replace("&", "");

            fileName = fileName + "." + fileExt;

            // 파일 업로드 패스 셋팅
            String path = fileUploadPath;

            // 파일 ID 생성
            String fileId = UUID.randomUUID().toString();

            // 현재는 uploadPath/날짜/아이디 로
            String folderPath = this.makeFolder(path, fileId);

            // 저장 파일명 처리
            String savePath = this.combineFilePath(folderPath, fileName);
            // 저장경로 normalize 및 whitelist 체크
            // 저장경로 문제가 있는경우 throw BIZ0007
            String saveFullPath = this.normalizeFilePathAndWhiteListCheck(this.combineFilePath(path, savePath));
            if(saveFullPath == null) throw new Exception("파일 경로 정보가 잘못되었습니다.");

            Path saveFilePath = Paths.get(saveFullPath);

            try {
                // 파일 저장
                multipartFile.transferTo(saveFilePath);
            } catch (IOException ioe) {
                log.error(ioe.getMessage());
                throw new Exception("파일 저장에 실패했습니다.");
            }

            /*
             * 파일 테이블 저장
             */
            // 파일 id
            reqVO.setFileId(fileId);
            // 파일 명
            reqVO.setFileNm(fileName);
            // 파일 경로
            reqVO.setFilePath(savePath);
            // 파일 확장자
            reqVO.setFileEnts(multipartFile.getContentType());
            // 파일 유형
            reqVO.setFilePtrn(fileExt);
            // 파일 크기
            reqVO.setFileSize(multipartFile.getSize());
            // 파일 정보 등록
            fileRepository.insertFileInfo(reqVO);

            return fileId;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 파일 다운로드
     * @param fileId
     * @param response
     * @throws Exception
     */
    @Override
    public void downloadFile(String fileId, HttpServletResponse response) throws Exception {
        try {
            // response 버퍼에 남아있는 데이터 삭제
            response.reset();

            if(StringUtils.isBlank(fileId)) throw new Exception("파일 ID 가 존재하지 않습니다.");

            FileVO fileVO = this.searchFileInfo(fileId);
            if(fileVO == null) throw new Exception("해당 파일이 존재하지 않습니다.");

            // 파일정보 헤더 세팅
            response.setHeader("Content-Type", fileVO.getFilePtrn());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileVO.getFileNm(), StandardCharsets.UTF_8.name()) + "\"");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Pragma", "no-cache;");
            response.setHeader("Expires", "-1;");

            // 파일 다운로드 CORS 처리를 위한 헤더 세팅
            response.setHeader("Access-Control-Allow-Origin", "*");

            // 파일 스트림 다운로드
            try (OutputStream outputStream = response.getOutputStream()) {

                String fileFullPath;

                fileFullPath = this.combineFilePath(fileUploadPath, fileVO.getFilePath());

                // 조회 파일경로 normalize 처리
                // 검증 fail 시 Exception
                fileFullPath = this.normalizeFilePathAndWhiteListCheck(fileFullPath);
                if(fileFullPath == null) throw new Exception("파일 경로 정보가 잘못되었습니다.");

                try(FileInputStream fis = new FileInputStream(fileFullPath)) {
                    byte[] data = new byte[8096]; //버퍼 크기 설정
                    int len = -1;
                    while ((len = fis.read(data)) != -1) {
                        outputStream.write(data, 0, len);
                    } // 파일이 남아 있면 읽어서(read) data에 저장(write)
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 폴더경로 생성
     * @param rootPath
     * @param uuid
     * @return
     * @throws Exception
     */
    private String makeFolder(String rootPath, String uuid) throws Exception {
        String folderPath = LocalDate.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        folderPath = this.combineFilePath(folderPath, uuid);

        // @Value 경로
        File uploadPathFolder = new File(rootPath, folderPath);

        //기존 경로 같은 폴더 파일이 없을 때만 mkdirs()로 위 폴더들을 생성
        if (!uploadPathFolder.exists()) {
            boolean result = uploadPathFolder.mkdirs();
            if(!result) {
                throw new Exception("파일 경로 생성 실패했습니다.");
            }
        }

        return folderPath;
    }

    /**
     * 파일 경로 합성
     * @param pre
     * @param suf
     * @return
     * @throws Exception
     */
    private String combineFilePath(String pre, String suf) throws Exception {
        boolean isPreEndWithSeparator = (pre.endsWith("/") || pre.endsWith("\\"));
        boolean isSufStartWithSeparator = (suf.startsWith("/") || suf.startsWith("\\"));

        // 양쪽 사이에 모두 구분자가 있을 경우, 한쪽의 구분자를 없애고 처리
        if(isPreEndWithSeparator && isSufStartWithSeparator) return pre.substring(0, pre.length() - 1) + suf;
        // 양쪽 사이에 모두 구분자가 없는 경우, 구분자 추가
        if(!isPreEndWithSeparator && !isSufStartWithSeparator) return pre + File.separator + suf;
        // 그 외 (어느 한쪽 중 구분자가 있음)
        return pre + suf;
    }

    /**
     * 파일 경로 점검
     * @param fileFullPath
     * @return
     * @throws Exception
     */
    private String normalizeFilePathAndWhiteListCheck(String fileFullPath) throws Exception {
        // fileFullPath 값이 비어있는 경우 null 반환
        if(StringUtils.isBlank(fileFullPath)) return null;

        // unix system check 및 신규 변수에 할당
        boolean isUnix = File.separator.equals("/");
        String checkPath = isUnix ? fileFullPath.replace("\\", File.separator) : fileFullPath.replace("/", File.separator);

        // 파일 경로 normalize 처리
        String normalized = FilenameUtils.normalizeNoEndSeparator(checkPath, isUnix);
        // normalize 결과가 null이거나 기존 path와 달라진 경우 null 반환
        if(normalized == null || !normalized.equals(checkPath)) return null;

        return normalized;
    }
}
