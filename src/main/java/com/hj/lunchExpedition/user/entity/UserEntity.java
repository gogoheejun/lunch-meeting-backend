package com.hj.lunchExpedition.user.entity;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class UserEntity {
    private Long id;
    private String kakaoId;
    private String nickname;
    private String selfIntroduction;
    private String approveStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
