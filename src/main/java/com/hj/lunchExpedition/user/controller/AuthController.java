// AuthController.java (JWT 추가)
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
    public ResponseEntity<BizResponse<TokenResponseDto>> tempLogin(@RequestBody TempLoginQDto dto) {
        try {
            TokenResponseDto res = userService.tempLogin(dto);
            return ResponseEntity.ok(BizResponse.success(res));
        } catch (Exception e) {
            return ResponseEntity.ok(BizResponse.fail("LOGIN_FAILED", e.getMessage()));
        }
    }
}
