package com.hj.lunchExpedition.meeting.mapper;

import com.hj.lunchExpedition.meeting.dto.GetAppliedMeetingsRDto;
import com.hj.lunchExpedition.meeting.dto.GetMeetingRDto;
import com.hj.lunchExpedition.meeting.dto.GetMyMeetingsRDto;
import com.hj.lunchExpedition.meeting.entity.MeetingEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MeetingMapper {

    int selectMeetingMakeCount(@Param("hostId") Long hostId,
                               @Param("startOfDay") LocalDateTime startOfDay,
                               @Param("endOfDay") LocalDateTime endOfDay);

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

    int countMeetingOwnedByHost(@Param("meetingId") int meetingId, @Param("hostId") Long hostId);
}
