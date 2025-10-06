package com.hj.lunchExpedition.meeting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMeetingQDto {
    private int page;   // 요청 페이지 번호 (1부터 시작)
    private int size;   // 페이지당 개수 (기본 20)
}