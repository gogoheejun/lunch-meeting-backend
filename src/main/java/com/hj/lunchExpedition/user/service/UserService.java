// UserService.java (일부 수정)
package com.hj.lunchExpedition.user.service;

import com.hj.lunchExpedition.user.dto.*;
import com.hj.lunchExpedition.user.entity.UserEntity;
import com.hj.lunchExpedition.user.mapper.UserMapper;
import com.hj.lunchExpedition.user.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public UserRDto tempSignup(TempSignupQDto dto) {
        if (dto == null || !StringUtils.hasText(dto.getNickname())) {
            throw new IllegalArgumentException("NICKNAME_REQUIRED");
        }
        String nickname = dto.getNickname().trim();

        UserEntity exists = userMapper.findByNickname(nickname);
        if (exists != null) {
            throw new IllegalStateException("DUPLICATE_NICKNAME");
        }

        UserEntity toSave = UserEntity.builder()
                .nickname(nickname)
                .selfIntroduction(dto.getSelfIntroduction())
                .build();
        userMapper.insert(toSave);

        return UserRDto.builder()
                .id(toSave.getId())
                .nickname(toSave.getNickname())
                .selfIntroduction(toSave.getSelfIntroduction())
                .build();
    }

    public TokenResponseDto tempLogin(TempLoginQDto dto) {
        if (dto == null || (dto.getUserId() == null && !StringUtils.hasText(dto.getNickname()))) {
            throw new IllegalArgumentException("LOGIN_KEY_REQUIRED");
        }

        UserEntity user = null;
        if (dto.getUserId() != null) {
            user = userMapper.findById(dto.getUserId());
        } else {
            user = userMapper.findByNickname(dto.getNickname().trim());
        }

        if (user == null) {
            throw new IllegalStateException("USER_NOT_FOUND");
        }

        // JWT 발급
        String token = jwtTokenProvider.generateToken(user.getId(), user.getNickname());

        UserRDto userRDto = UserRDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .selfIntroduction(user.getSelfIntroduction())
                .build();

        return TokenResponseDto.builder()
                .token(token)
                .user(userRDto)
                .build();
    }
}
