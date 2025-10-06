package com.hj.lunchExpedition.user.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class TokenResponseDto {
    private String token;
    private UserRDto user;
}
