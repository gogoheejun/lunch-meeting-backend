package com.hj.lunchExpedition.meeting.mapper;

import com.hj.lunchExpedition.meeting.dto.GetAppliedMeetingsRDto;
import com.hj.lunchExpedition.meeting.dto.GetMeetingRDto;
import com.hj.lunchExpedition.meeting.dto.GetMyMeetingsRDto;
import com.hj.lunchExpedition.meeting.dto.MeetingApplicantRDto;
import com.hj.lunchExpedition.meeting.entity.MeetingEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeetingMapper {

    int selectMeetingMakeCount();

    int createMeeting(MeetingEntity meeting);

    int countAllMeetings();
    List<GetMeetingRDto> selectMeetingList(@Param("offset") int offset, @Param("size") int size);

    int insertMeetingApplication(
            @Param("meetingId") int meetingId,
            @Param("applicantId") int applicantId,
            @Param("status") String status
    );

    List<GetMyMeetingsRDto> selectMyMeetings(Long hostId);

    List<GetAppliedMeetingsRDto> selectAppliedMeetings(Long applicantId);

    List<MeetingApplicantRDto> selectApplicantsByMeeting(@Param("meetingId") int meetingId);
}
