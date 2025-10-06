package com.hj.lunchExpedition.meeting.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class GetMeetingListRDto {
    private List<GetMeetingRDto> meetingList;
    private int totalCount;   // 전체 데이터 개수
    private boolean hasNext;  // 다음 페이지 존재 여부
}
