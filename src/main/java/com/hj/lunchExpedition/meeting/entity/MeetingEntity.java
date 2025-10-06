package com.hj.lunchExpedition.meeting.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
public class MeetingEntity {
    private Long id;
    private Long hostId;
    private String title;
    private String meetingPlace;
    private String restaurantName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxParticipantsCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
