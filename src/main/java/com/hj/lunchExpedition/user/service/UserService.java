package com.hj.lunchExpedition.user.service;

import com.hj.lunchExpedition.user.dto.*;
import com.hj.lunchExpedition.user.entity.UserEntity;
import com.hj.lunchExpedition.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public UserRDto tempSignup(TempSignupQDto dto) {
        // 1) 유효성
        if (dto == null || !StringUtils.hasText(dto.getNickname())) {
            throw new IllegalArgumentException("NICKNAME_REQUIRED");
        }
        String nickname = dto.getNickname().trim();

        // 2) 중복 체크
        UserEntity exists = userMapper.findByNickname(nickname);
        if (exists != null) {
            // BizResponse.fail 사용 중이라면 Controller에서 fail로 변환
            throw new IllegalStateException("DUPLICATE_NICKNAME");
        }

        // 3) 저장
        UserEntity toSave = UserEntity.builder()
                .nickname(nickname)
                .selfIntroduction(dto.getSelfIntroduction())
                .build();

        userMapper.insert(toSave); // id 채워짐(useGeneratedKeys)

        return UserRDto.builder()
                .id(toSave.getId())
                .nickname(toSave.getNickname())
                .selfIntroduction(toSave.getSelfIntroduction())
                .build();
    }

    public UserRDto tempLogin(TempLoginQDto dto) {
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
            // BizResponse.fail 사용 중이라면 Controller에서 fail로 변환
            throw new IllegalStateException("USER_NOT_FOUND");
        }

        return UserRDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .selfIntroduction(user.getSelfIntroduction())
                .build();
    }
}
