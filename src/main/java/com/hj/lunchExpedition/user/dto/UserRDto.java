// UserRDto.java
package com.hj.lunchExpedition.user.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class UserRDto {
    private Long id;
    private String nickname;
    private String selfIntroduction;
}
