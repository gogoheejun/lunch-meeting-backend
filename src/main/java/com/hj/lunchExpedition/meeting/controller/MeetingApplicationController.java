// MeetingApplicationController.java
package com.hj.lunchExpedition.meeting.controller;

import com.hj.lunchExpedition.common.BizResponse;
import com.hj.lunchExpedition.meeting.dto.GetApplicantsQDto;
import com.hj.lunchExpedition.meeting.dto.MeetingApplicantRDto;
import com.hj.lunchExpedition.meeting.dto.UpdateApplicationStatusQDto;
import com.hj.lunchExpedition.meeting.service.MeetingApplicationService;
import jakarta.servlet.http.HttpServletRequest;
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
            HttpServletRequest request,
            @RequestBody UpdateApplicationStatusQDto updateDto
    ) {
        Long hostId = getUserIdFromRequest(request);
        boolean result = meetingApplicationService.updateApplicationStatus(hostId, updateDto);
        return ResponseEntity.ok(BizResponse.success(result));
    }

    // 특정 모임의 신청자 목록 조회
    @PostMapping("/list")
    public ResponseEntity<BizResponse<List<MeetingApplicantRDto>>> getApplicantsByMeeting(
            HttpServletRequest request,
            @RequestBody GetApplicantsQDto getApplicantsQDto
    ) {
        Long hostId = getUserIdFromRequest(request);
        List<MeetingApplicantRDto> applicants = meetingApplicationService.getApplicantsByMeeting(hostId, getApplicantsQDto.getMeetingId());
        return ResponseEntity.ok(BizResponse.success(applicants));
    }

    private Long getUserIdFromRequest(HttpServletRequest request) {
        Object userIdAttr = request.getAttribute("userId");
        if (userIdAttr instanceof Long userId) {
            return userId;
        }
        if (userIdAttr instanceof Integer intUserId) {
            return Long.valueOf(intUserId);
        }
        throw new IllegalStateException("인증 정보가 존재하지 않습니다.");
    }
}
