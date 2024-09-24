package com.lecture.study.app.filter;

import com.lecture.study.app.utils.EncryptionUtils;
import com.lecture.study.app.utils.JwtTokenUtil;
import com.lecture.study.biz.service.user.UserService;
import com.lecture.study.biz.service.user.vo.UserInfoVO;
import com.lecture.study.biz.service.user.vo.UserReqVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class LoginFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public LoginFilter(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, RuntimeException {

        final String requestTokenHeader = request.getHeader("Authorization");

        try {

            String jwtToken = null;
            String userId = null;

            // requestTokenHeader 가 존재하면서, Bearer 로 시작하는 경우, jwt 추출
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                userId = EncryptionUtils.decrypt(jwtTokenUtil.getClaimFromToken(jwtToken, Claims::getSubject));
            }

            // 토큰에 들어 있는 아이디가 있고 스프링 컨텍스트에 인증이 되어 있지 않으며, 토큰의 만료시간이 지나지 않은 경우
            if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null && !jwtTokenUtil.isTokenExpired(jwtToken)) {
                //들어온 유저를 검색해서 체크
                UserDetails userDetails = this.loadUserDetail(userId);
                if(userDetails != null) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

        } catch (IllegalArgumentException e) {
            logger.error("토큰이 존재하지 않습니다.");
        } catch (ExpiredJwtException e) {
            logger.error("만료된 토큰 입니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    /**
     * UserDetail 작성
     * @param userId
     * @return
     */
    private UserDetails loadUserDetail(String userId) throws Exception {

        // 유저 정보 조회
        UserReqVO userReqVO = new UserReqVO();
        userReqVO.setUserId(userId);
        UserInfoVO userInfoVO = userService.searchUserInfo(userReqVO);

        if(userInfoVO == null) {
            throw new Exception("해당 유저가 존재하지 않습니다.");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        // 권한 설정
        authorities.add(new SimpleGrantedAuthority(userInfoVO.getSysAdmYn().equals("Y") ? "ADMIN" : "USER"));

        // 유저 셋팅
        return new User(userInfoVO.getUserId(), "PROTECTED", authorities);
    }
}
