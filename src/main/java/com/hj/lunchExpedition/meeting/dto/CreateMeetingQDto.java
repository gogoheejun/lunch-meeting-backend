package com.hj.lunchExpedition.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMeetingQDto {
    private String title;
    private String meetingPlace;
    private String restaurantName;
    private String startTime;
    private String endTime;
}
