package com.lecture.study.app.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class JwtTokenUtil {

    // secretKey
    @Value("${jwt.secretKey}")
    private String secretKey;

    // expiredTime
    @Value("${jwt.expiredTime}")
    private long expiredTime;

    /**
     * jwt 토큰 작성
     */
    public String makeJwt(String id) throws Exception {

        try {
            //token Header 생성
            Map<String, Object> headerMap = new HashMap<String, Object>();
            headerMap.put("typ", "JWT");
            headerMap.put("alg", "HS256");

            //token Claims 생성
            Map<String, Object> claims = new HashMap<>();
            String encId = EncryptionUtils.encrypt(id);
            claims.put("USERID", encId);

            @SuppressWarnings("deprecation")
            JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                    .setClaims(claims)
                    .setSubject(encId) // 유효검증용 subject 설정
                    .setIssuedAt(new Date(System.currentTimeMillis())) //생성일
                    .setExpiration(new Date(System.currentTimeMillis() + expiredTime)) //만료일
                    .signWith(SignatureAlgorithm.HS256, secretKey);

            String jwtToken = builder.compact();

            return jwtToken;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    /**
     * jwt Claim 확인
     * @param token
     * @return
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    /**
     * 토큰 유효기간 체크
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = this.getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}
