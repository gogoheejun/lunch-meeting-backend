package com.hj.lunchExpedition.user.controller;

import com.hj.lunchExpedition.common.BizResponse;
import com.hj.lunchExpedition.user.dto.*;
import com.hj.lunchExpedition.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    // 임시 회원가입
    @PostMapping("/temp-signup")
    public ResponseEntity<BizResponse<UserRDto>> tempSignup(@RequestBody TempSignupQDto dto) {
        try {
            UserRDto res = userService.tempSignup(dto);
            return ResponseEntity.ok(BizResponse.success(res));
        } catch (IllegalArgumentException e) {
            // 예: NICKNAME_REQUIRED
            return ResponseEntity.ok(BizResponse.fail("INVALID_PARAM", e.getMessage()));
        } catch (IllegalStateException e) {
            // 예: DUPLICATE_NICKNAME
            return ResponseEntity.ok(BizResponse.fail(e.getMessage(), "요청을 처리할 수 없습니다."));
        }
    }

    // 임시 로그인
    @PostMapping("/temp-login")
    public ResponseEntity<BizResponse<UserRDto>> tempLogin(@RequestBody TempLoginQDto dto) {
        try {
            UserRDto res = userService.tempLogin(dto);
            return ResponseEntity.ok(BizResponse.success(res));
        } catch (IllegalArgumentException e) {
            // 예: LOGIN_KEY_REQUIRED
            return ResponseEntity.ok(BizResponse.fail("INVALID_PARAM", e.getMessage()));
        } catch (IllegalStateException e) {
            // 예: USER_NOT_FOUND
            return ResponseEntity.ok(BizResponse.fail(e.getMessage(), "가입된 사용자가 없습니다."));
        }
    }
}
