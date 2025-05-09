package com.project.shopApp.mapper;

import com.project.shopApp.dtos.UserDto;
import com.project.shopApp.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UserMapping {
    UserMapping INSTANCE = Mappers.getMapper(UserMapping.class);

    UserDto toDto(User user);

    List<UserDto> toListDto(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    User toModel(UserDto userDto);

    List<User> toListModel(List<UserDto> userDtos);
}
