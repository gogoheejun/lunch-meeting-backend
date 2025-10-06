// MeetingApplicantRDto.java
package com.hj.lunchExpedition.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingApplicantRDto {
    private int id;
    private int applicantId;
    private String nickname;
    private String selfIntroduction;
    private String status;
    private LocalDateTime appliedAt;
    private LocalDateTime respondedAt;
}
