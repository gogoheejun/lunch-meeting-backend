// UpdateApplicationStatusQDto.java
package com.hj.lunchExpedition.meeting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateApplicationStatusQDto {
    private int meetingId;      // 모임 ID
    private int applicantId;    // 신청자 ID
    private String status;      // 바꿀 상태 (APPROVED or REJECTED)
}
