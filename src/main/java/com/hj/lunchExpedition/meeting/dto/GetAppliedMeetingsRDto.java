package com.hj.lunchExpedition.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAppliedMeetingsRDto {
    private Long meetingId;
    private String title;
    private String meetingPlace;
    private String restaurantName;
    private String startTime;
    private String endTime;
    private Integer maxParticipantsCount;
    private String status;
    private String appliedAt;
}
