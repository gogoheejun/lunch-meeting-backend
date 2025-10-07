package com.hj.lunchExpedition.common;

import com.hj.lunchExpedition.user.util.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // ✅ JWT 검사 제외 경로
        if (path.startsWith("/api/temp") || path.endsWith("/api/auth/me")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ 쿠키에서 jwtToken 추출
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwtToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // ✅ 토큰이 없거나 유효하지 않으면 401 응답
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("""
                    {
                      "success": false,
                      "code": "INVALID_TOKEN",
                      "message": "유효하지 않은 JWT 토큰입니다."
                    }
                    """);
            return;
        }

        // ✅ 토큰 유효 → 요청 속성에 사용자 ID 저장 후 다음 필터로 진행
        Long userId = jwtTokenProvider.getUserId(token);
        request.setAttribute("userId", userId);
        filterChain.doFilter(request, response);
    }
}
