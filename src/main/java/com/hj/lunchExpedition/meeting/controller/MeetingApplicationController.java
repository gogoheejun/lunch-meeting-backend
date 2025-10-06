// MeetingApplicationController.java
package com.hj.lunchExpedition.meeting.controller;

import com.hj.lunchExpedition.common.BizResponse;
import com.hj.lunchExpedition.meeting.dto.GetApplicantsQDto;
import com.hj.lunchExpedition.meeting.dto.MeetingApplicantRDto;
import com.hj.lunchExpedition.meeting.dto.UpdateApplicationStatusQDto;
import com.hj.lunchExpedition.meeting.service.MeetingApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/meeting/application")
public class MeetingApplicationController {

    private final MeetingApplicationService meetingApplicationService;

    // 신청 승인 / 거절
    @PostMapping("/updateStatus")
    public ResponseEntity<BizResponse<Boolean>> updateApplicationStatus(
            @RequestBody UpdateApplicationStatusQDto updateDto
    ) {
        boolean result = meetingApplicationService.updateApplicationStatus(updateDto);
        return ResponseEntity.ok(BizResponse.success(result));
    }

    // 특정 모임의 신청자 목록 조회
    @PostMapping("/list")
    public ResponseEntity<BizResponse<List<MeetingApplicantRDto>>> getApplicantsByMeeting(
            @RequestBody GetApplicantsQDto getApplicantsQDto
    ) {
        List<MeetingApplicantRDto> applicants = meetingApplicationService.getApplicantsByMeeting(getApplicantsQDto.getMeetingId());
        return ResponseEntity.ok(BizResponse.success(applicants));
    }
}
