package com.lecture.study.biz.service.sns.kakao;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lecture.study.biz.service.sns.kakao.vo.KakaoUserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
public class KakaoServiceImpl implements KakaoService {

    @Value("${server.front.url}")
    private String frontUrl;

    /**
     * 카카오 로그인 accessToken 요청
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public String getAccessToken(String code) throws Exception {
        try {
            String accessToken = "";
            String refreshToken = "";
            String reqUrl = "https://kauth.kakao.com/oauth/token";

            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&code=").append(code);
            sb.append("&redirect_uri=" + frontUrl + "/kakao/oauth");
            sb.append("&client_id=7966e95eb01275def0f1be611059a038");

            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            log.info("responseCode = {}", responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }

            log.info("result = {}", result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            accessToken = element.getAsJsonObject().get("access_token").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

            log.info("accessToken = {}", accessToken);
            log.info("refreshToken = {}", refreshToken);

            br.close();
            bw.close();

            conn.disconnect();

            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * 카카오 로그인 유저 정보 요청
     * @param accessToken
     * @return
     * @throws Exception
     */
    @Override
    public KakaoUserInfoVO getUserInfo(String accessToken) throws Exception {
        try {
            KakaoUserInfoVO resultVO = new KakaoUserInfoVO();
            String reqUrl = "https://kapi.kakao.com/v2/user/me";

            URL url1 = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
            log.info("responseCode = {}", responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("result = {}", result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            String nickname = properties.get("nickname").getAsString();

            String id = element.getAsJsonObject().get("id").getAsString();

            resultVO.setId(id);
            resultVO.setNickname(nickname);

            return resultVO;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 카카오 로그아웃
     * @param accessToken
     * @throws Exception
     */
    @Override
    public void logout(String accessToken) throws Exception {
        try {
            String reqUrl = "https://kapi.kakao.com/v1/user/unlink";
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
            log.info("responseCode = {}", responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("result = {}", result);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
