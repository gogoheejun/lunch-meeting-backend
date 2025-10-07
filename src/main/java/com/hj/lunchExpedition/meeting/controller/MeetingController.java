package com.hj.lunchExpedition.meeting.controller;

import com.hj.lunchExpedition.common.BizResponse;
import com.hj.lunchExpedition.meeting.dto.*;
import com.hj.lunchExpedition.meeting.service.MeetingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    // 모임 만들기
    @PostMapping("createMeeting")
    public ResponseEntity<BizResponse> createMeeting(
            HttpServletRequest request,
            @RequestBody CreateMeetingQDto createMeetingQDto
    ) {
        Long userId = getUserIdFromRequest(request);
        meetingService.createMeeting(userId, createMeetingQDto);
        return ResponseEntity.ok(BizResponse.success());
    }

    // 모임 목록 조회
    @PostMapping("/getMeetingList")
    public ResponseEntity<BizResponse<GetMeetingListRDto>> getMeetingList(@RequestBody GetMeetingQDto meetingQDto) {
        GetMeetingListRDto meetingList= meetingService.getMeetingList(meetingQDto);
        return ResponseEntity.ok(BizResponse.success(meetingList));
    }

    // 모임 신청하기
    @PostMapping("/apply")
    public ResponseEntity<BizResponse<Boolean>> applyMeeting(
            HttpServletRequest request,
            @RequestBody ApplyMeetingQDto applyMeetingQDto
    ) {
        Long userId = getUserIdFromRequest(request);
        boolean res = meetingService.applyMeeting(userId, applyMeetingQDto);
        return ResponseEntity.ok(BizResponse.success(res));
    }

    // 내가 만든 모임 목록 조회
    @PostMapping("/my")
    public ResponseEntity<BizResponse<List<GetMyMeetingsRDto>>> getMyMeetings(
            HttpServletRequest request,
            @RequestBody(required = false) GetMyMeetingsQDto getMyMeetingsQDto
    ) {
        Long userId = getUserIdFromRequest(request);
        List<GetMyMeetingsRDto> res = meetingService.getMyMeetings(userId);
        return ResponseEntity.ok(BizResponse.success(res));
    }

    // 내가 신청한 모임 목록 조회
    @PostMapping("/applied")
    public ResponseEntity<BizResponse<List<GetAppliedMeetingsRDto>>> getAppliedMeetings(
            HttpServletRequest request,
            @RequestBody(required = false) GetAppliedMeetingsQDto getAppliedMeetingsQDto
    ) {
        Long userId = getUserIdFromRequest(request);
        List<GetAppliedMeetingsRDto> res = meetingService.getAppliedMeetings(userId);
        return ResponseEntity.ok(BizResponse.success(res));
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
