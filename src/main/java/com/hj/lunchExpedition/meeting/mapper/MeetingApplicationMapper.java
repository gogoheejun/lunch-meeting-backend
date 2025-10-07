// MeetingApplicationMapper.java
package com.hj.lunchExpedition.meeting.mapper;

import com.hj.lunchExpedition.meeting.dto.MeetingApplicantRDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingApplicationMapper {

    List<MeetingApplicantRDto> selectApplicantsByMeeting(@Param("meetingId") int meetingId,
                                                         @Param("hostId") Long hostId);

    int updateApplicationStatus(@Param("meetingId") int meetingId,
                                @Param("applicantId") int applicantId,
                                @Param("status") String status,
                                @Param("hostId") Long hostId);
}
