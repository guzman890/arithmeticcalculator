package com.ntd.arithmeticcalculator.service.mapper;

import com.ntd.arithmeticcalculator.model.dto.UserDto;
import com.ntd.arithmeticcalculator.model.entity.UserEntity;

public class UserMapper {

    public static UserDto toDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setCredits(userEntity.getCredits());
        userDto.setStatus(userEntity.getStatus());
        return userDto;
    }

    public static UserEntity toEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setCredits(userDto.getCredits());
        userEntity.setStatus(userDto.getStatus());
        return userEntity;
    }
}