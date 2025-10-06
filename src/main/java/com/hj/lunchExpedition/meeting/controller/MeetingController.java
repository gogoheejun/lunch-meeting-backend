package com.hj.lunchExpedition.meeting.controller;

import com.hj.lunchExpedition.common.BizResponse;
import com.hj.lunchExpedition.meeting.dto.*;
import com.hj.lunchExpedition.meeting.service.MeetingService;
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
    public ResponseEntity<BizResponse> createMeeting(@RequestBody CreateMeetingQDto createMeetingQDto) {
        meetingService.createMeeting(createMeetingQDto);
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
    public ResponseEntity<BizResponse<Boolean>> applyMeeting(@RequestBody ApplyMeetingQDto applyMeetingQDto) {
        boolean res = meetingService.applyMeeting(applyMeetingQDto);
        return ResponseEntity.ok(BizResponse.success(res));
    }

    // 내가 만든 모임 목록 조회
    @PostMapping("/my")
    public ResponseEntity<BizResponse<List<GetMyMeetingsRDto>>> getMyMeetings(
            @RequestBody GetMyMeetingsQDto getMyMeetingsQDto
    ) {
        List<GetMyMeetingsRDto> res = meetingService.getMyMeetings(getMyMeetingsQDto.getHostId());
        return ResponseEntity.ok(BizResponse.success(res));
    }

    // 내가 신청한 모임 목록 조회
    @PostMapping("/applied")
    public ResponseEntity<BizResponse<List<GetAppliedMeetingsRDto>>> getAppliedMeetings(
            @RequestBody GetAppliedMeetingsQDto getAppliedMeetingsQDto
    ) {
        List<GetAppliedMeetingsRDto> res = meetingService.getAppliedMeetings(getAppliedMeetingsQDto.getApplicantId());
        return ResponseEntity.ok(BizResponse.success(res));
    }

}
