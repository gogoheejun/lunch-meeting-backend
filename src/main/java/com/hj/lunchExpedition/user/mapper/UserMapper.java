package com.hj.lunchExpedition.user.mapper;

import com.hj.lunchExpedition.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insert(UserEntity user);                    // useGeneratedKeys
    UserEntity findById(@Param("id") Long id);
    UserEntity findByNickname(@Param("nickname") String nickname);
}
