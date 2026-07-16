package com.learnhub.mapper;

import java.util.List;
import org.mapstruct.Mapper;

import com.learnhub.dto.RegisterRequest;
import com.learnhub.dto.UserDto;
import com.learnhub.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto toDto(User user);

	User toEntity(UserDto dto);

	List<UserDto> toDtoList(List<User> users);

	List<User> toEntityList(List<UserDto> dtos);
	
    // RegisterRequest -> User Entity
    User toEntity(RegisterRequest request);


}