// MeetingApplicationMapper.java
package com.hj.lunchExpedition.meeting.mapper;

import com.hj.lunchExpedition.meeting.dto.UpdateApplicationStatusQDto;
import com.hj.lunchExpedition.meeting.dto.MeetingApplicantRDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingApplicationMapper {

    List<MeetingApplicantRDto> selectApplicantsByMeeting(int meetingId);

    int updateApplicationStatus(UpdateApplicationStatusQDto dto);
}
