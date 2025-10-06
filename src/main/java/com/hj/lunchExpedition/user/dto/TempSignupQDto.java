package com.hj.lunchExpedition.user.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TempSignupQDto {
    private String nickname;           // required
    private String selfIntroduction;   // optional
}
