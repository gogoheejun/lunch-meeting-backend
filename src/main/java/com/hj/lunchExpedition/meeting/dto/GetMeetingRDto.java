package com.hj.lunchExpedition.meeting.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMeetingRDto {
    private Long id;
    private Long hostId;
    private String title;
    private String meetingPlace;
    private String restaurantName;
    private String startTime;
    private String endTime;
    private Integer maxParticipantsCount;
    private String createdAt;
    private String updatedAt;
}