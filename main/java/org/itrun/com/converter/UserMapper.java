package org.itrun.com.converter;

import org.itrun.com.dto.CreateUserDto;
import org.itrun.com.dto.EditUserDto;
import org.itrun.com.dto.UserDto;
import org.itrun.com.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "type", target = "type")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "mobile", target = "mobile")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "pesel", target = "pesel")
    UserDto userToUserDto(User user);

    CreateUserDto userToCreateUserDto(User user);

    User userDtoToUser(UserDto userDto);

    @Mapping(source = "type", target = "type")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "mobile", target = "mobile")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "pesel", target = "pesel")
    User createUserDtoToUser(CreateUserDto createUserDto);

    EditUserDto userToEditUserDto(User user);

    User editUserDtoToUser(EditUserDto editUserDto);
}
