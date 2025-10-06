// AuthController.java (JWT 추가)
package com.hj.lunchExpedition.user.controller;

import com.hj.lunchExpedition.common.BizResponse;
import com.hj.lunchExpedition.user.dto.*;
import com.hj.lunchExpedition.user.entity.UserEntity;
import com.hj.lunchExpedition.user.mapper.UserMapper;
import com.hj.lunchExpedition.user.service.UserService;
import com.hj.lunchExpedition.user.util.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    @PostMapping("/temp-signup")
    public ResponseEntity<BizResponse<UserRDto>> tempSignup(@RequestBody TempSignupQDto dto) {
        try {
            UserRDto res = userService.tempSignup(dto);
            return ResponseEntity.ok(BizResponse.success(res));
        } catch (Exception e) {
            return ResponseEntity.ok(BizResponse.fail("SIGNUP_FAILED", e.getMessage()));
        }
    }

    @PostMapping("/temp-login")
    public ResponseEntity<BizResponse<TokenResponseDto>> tempLogin(
            @RequestBody TempLoginQDto dto,
            HttpServletResponse response) {
        try {
            TokenResponseDto res = userService.tempLogin(dto);

            // ✅ JWT를 HttpOnly 쿠키로 설정
            ResponseCookie cookie = ResponseCookie.from("jwtToken", res.getToken())
                    .httpOnly(true)
                    .secure(false) // 로컬에서는 false, https 환경에서는 true
                    .path("/")
                    .maxAge(Duration.ofDays(7))
                    .sameSite("Lax")
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return ResponseEntity.ok(BizResponse.success(res));
        } catch (Exception e) {
            return ResponseEntity.ok(BizResponse.fail("LOGIN_FAILED", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<BizResponse<UserRDto>> getMyInfo(
            @CookieValue(value = "jwtToken", required = false) String token) {

        if (token == null || token.isEmpty()) {
            return ResponseEntity.ok(BizResponse.fail("INVALID_TOKEN", "토큰이 없습니다."));
        }

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.ok(BizResponse.fail("INVALID_TOKEN", "유효하지 않은 토큰입니다."));
        }

        Long userId = jwtTokenProvider.getUserId(token);
        UserEntity user = userMapper.findById(userId);
        if (user == null) {
            return ResponseEntity.ok(BizResponse.fail("USER_NOT_FOUND", "사용자를 찾을 수 없습니다."));
        }

        UserRDto dto = new UserRDto(user.getId(), user.getNickname(), user.getSelfIntroduction());
        return ResponseEntity.ok(BizResponse.success(dto));
    }


}
