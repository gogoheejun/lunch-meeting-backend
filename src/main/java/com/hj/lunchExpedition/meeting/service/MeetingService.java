package com.hj.lunchExpedition.meeting.service;

import com.hj.lunchExpedition.meeting.dto.*;
import com.hj.lunchExpedition.meeting.entity.MeetingEntity;
import com.hj.lunchExpedition.meeting.mapper.MeetingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MeetingService {

    private final MeetingMapper meetingMapper;

    public void createMeeting(CreateMeetingQDto dto){

        //중복 체크: 하루 두건만 등록가능
        int meetingMakeCnt = meetingMapper.selectMeetingMakeCount();
        if(1 < meetingMakeCnt){
            throw new RuntimeException("하루에 2개까지만 만들기 가능");
        }

        MeetingEntity entity = MeetingEntity.builder()
                .hostId(100L)
                .title(dto.getTitle())
                .meetingPlace(dto.getMeetingPlace())
                .restaurantName(dto.getRestaurantName())
                .startTime(LocalDateTime.parse(dto.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm[:ss]")))
                .endTime(LocalDateTime.parse(dto.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm[:ss]")))
                .maxParticipantsCount(4)
                .build();
        meetingMapper.createMeeting(entity);
    }

    public GetMeetingListRDto getMeetingList(GetMeetingQDto qDto) {
        int page = qDto.getPage() <= 0 ? 1 : qDto.getPage();
        int size = qDto.getSize() <= 0 ? 20 : qDto.getSize();
        int offset = (page - 1) * size;

        List<GetMeetingRDto> list = meetingMapper.selectMeetingList(offset, size);
        int totalCount = meetingMapper.countAllMeetings();
        boolean hasNext = (offset + size) < totalCount;

        return GetMeetingListRDto.builder()
                .meetingList(list)
                .totalCount(totalCount)
                .hasNext(hasNext)
                .build();
    }

    public boolean applyMeeting(ApplyMeetingQDto dto) {

        // todo: 귀찮아서 나중에 처리..ㅋㅋ
        // 본인이 본인꺼 요청 못함

        // 중복요청 체크

        // 모임 시간 지났는지 체크

        // 4명이 모두 찼는지 체크

        int insertedRows = meetingMapper.insertMeetingApplication(
                dto.getMeetingId(),
                dto.getApplicantId(),
                "PENDING"
        );

        return insertedRows > 0;
    }


    public List<GetMyMeetingsRDto> getMyMeetings(Long hostId) {
        return meetingMapper.selectMyMeetings(hostId);
    }

    public List<GetAppliedMeetingsRDto> getAppliedMeetings(Long applicantId) {
        return meetingMapper.selectAppliedMeetings(applicantId);
    }
}
