package org.itrun.com.controller;

import org.itrun.com.converter.UserMapper;
import org.itrun.com.dto.CreateUserDto;
import org.itrun.com.dto.UserDto;
import org.itrun.com.entity.User;
import org.itrun.com.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(userMapper);

        Mockito.lenient().when(userMapper.userToUserDto(Mockito.any(User.class)))
                .thenAnswer(invocation -> {
                    User user = invocation.getArgument(0);
                    return new UserDto(user.getId(), user.getFirstName());
                });
    }

    @Test
    public void testCreateUser() {
        CreateUserDto createUserDto = new CreateUserDto();
        User user = new User();
        UserDto userDto = new UserDto();

        when(userMapper.createUserDtoToUser(createUserDto)).thenReturn(user);
        when(userService.create(user)).thenReturn(user);
        when(userMapper.userToUserDto(user)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.create(createUserDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userMapper).createUserDtoToUser(createUserDto);
        verify(userService).create(user);
        verify(userMapper).userToUserDto(user);
    }

    @Test
    public void testGetAllUsers() {

        List<User> userList = new ArrayList<>();
        userList.add(new User());
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(new UserDto());

        when(userService.getAll()).thenReturn(userList);
        when(userMapper.userToUserDto(any())).thenReturn(new UserDto());

        ResponseEntity<List<UserDto>> response = userController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDtoList, response.getBody());
        verify(userService).getAll();
        verify(userMapper).userToUserDto(any());
    }

    @Test
    public void testDeleteUser() {

        long userId = 1L;
        ResponseEntity<Void> response = userController.deleteById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).delete(userId);
    }

    @Test
    public void testSearchUsers() {

        List<User> userList = new ArrayList<>();
        when(userService.searchUsers(any())).thenReturn(userList);

        ResponseEntity<List<UserDto>> response = userController.searchUsers(null);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService).searchUsers(any());
        verifyNoInteractions(userMapper);
    }
}