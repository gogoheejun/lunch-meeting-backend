// MeetingApplicationService.java
package com.hj.lunchExpedition.meeting.service;

import com.hj.lunchExpedition.meeting.dto.MeetingApplicantRDto;
import com.hj.lunchExpedition.meeting.dto.UpdateApplicationStatusQDto;
import com.hj.lunchExpedition.meeting.mapper.MeetingApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MeetingApplicationService {

    private final MeetingApplicationMapper meetingApplicationMapper;

    public boolean updateApplicationStatus(UpdateApplicationStatusQDto dto) {
        int updated = meetingApplicationMapper.updateApplicationStatus(dto);
        if(updated < 1) throw new RuntimeException("updateApplicationStatus 실패");
        return updated > 1;
    }

    public List<MeetingApplicantRDto> getApplicantsByMeeting(int meetingId) {
        return meetingApplicationMapper.selectApplicantsByMeeting(meetingId);
    }
}
