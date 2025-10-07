// MeetingApplicationService.java
package com.hj.lunchExpedition.meeting.service;

import com.hj.lunchExpedition.meeting.dto.MeetingApplicantRDto;
import com.hj.lunchExpedition.meeting.dto.UpdateApplicationStatusQDto;
import com.hj.lunchExpedition.meeting.mapper.MeetingMapper;
import com.hj.lunchExpedition.meeting.mapper.MeetingApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MeetingApplicationService {

    private final MeetingApplicationMapper meetingApplicationMapper;
    private final MeetingMapper meetingMapper;

    public boolean updateApplicationStatus(Long hostId, UpdateApplicationStatusQDto dto) {
        validateOwnership(hostId, dto.getMeetingId());

        int updated = meetingApplicationMapper.updateApplicationStatus(
                dto.getMeetingId(),
                dto.getApplicantId(),
                dto.getStatus(),
                hostId
        );

        if (updated < 1) {
            throw new RuntimeException("updateApplicationStatus 실패");
        }
        return updated > 0;
    }

    public List<MeetingApplicantRDto> getApplicantsByMeeting(Long hostId, int meetingId) {
        validateOwnership(hostId, meetingId);
        return meetingApplicationMapper.selectApplicantsByMeeting(meetingId, hostId);
    }

    private void validateOwnership(Long hostId, int meetingId) {
        if (hostId == null) {
            throw new IllegalStateException("인증 정보가 존재하지 않습니다.");
        }
        int ownedCount = meetingMapper.countMeetingOwnedByHost(meetingId, hostId);
        if (ownedCount < 1) {
            throw new IllegalArgumentException("해당 모임에 대한 권한이 없습니다.");
        }
    }
}
