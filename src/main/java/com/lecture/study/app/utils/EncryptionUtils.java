package com.lecture.study.app.utils;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Component
public class EncryptionUtils {

    // secretKey
    private static final String KEY = "studyprojectskey";

    // algorithm
    private static final String ALGORITHM = "AES";

    /**
     * AES 암호화
     * @param plainText
     * @return
     * @throws Exception
     */
    public static String encrypt(String plainText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * AES 복호화
     * @param encryptedText
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedText) throws Exception {
        // 파라메터 체크
        if(StringUtils.isBlank(encryptedText)) return null;
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * SHA-256 암호화
     * @param plainText
     * @return
     * @throws Exception
     */
    public static String sha256Encode(String plainText) throws Exception {
        return Hashing.sha256().hashString(plainText, StandardCharsets.UTF_8).toString();
    }
}
