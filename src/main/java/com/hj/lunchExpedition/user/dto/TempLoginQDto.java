package com.hj.lunchExpedition.user.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TempLoginQDto {
    private Long userId;       // 있으면 우선 사용
    private String nickname;   // userId 없을 때 대체
}
