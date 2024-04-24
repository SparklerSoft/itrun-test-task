package org.itrun.com.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.itrun.com.converter.UserMapper;
import org.itrun.com.dto.CreateUserDto;
import org.itrun.com.dto.EditUserDto;
import org.itrun.com.dto.UserDto;
import org.itrun.com.entity.User;
import org.itrun.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "User Controller", description = "Handles operations related to users")
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user based on the provided data",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created user"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto createUserDto) {

        User user = mapper.createUserDtoToUser(createUserDto);
        User createdUser = service.create(user);
        UserDto createdUserDto = mapper.userToUserDto(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @Operation(
            summary = "Get all users",
            description = "Retrieves a list of all users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved  users"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAll() {

        List<User> users = service.getAll();
        List<UserDto> userDtos = users.stream()
                .map(user -> mapper.userToUserDto(user))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(userDtos);
    }

    @Operation(
            summary = "Update a user",
            description = "Updates an existing user with the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated user"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<EditUserDto> editById(@PathVariable("id") long id, @RequestBody EditUserDto editUserDto) {

        User user = mapper.editUserDtoToUser(editUserDto);
        service.edit(id, user);
        EditUserDto updatedUserDto = mapper.userToEditUserDto(user);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUserDto);
    }

    @Operation(
            summary = "Get a user by ID",
            description = "Retrieves a user by its unique identifier",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") long id) {

        User user = service.getById(id);
        UserDto userDto = mapper.userToUserDto(user);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @Operation(
            summary = "Get a list of users by name",
            description = "Retrieves a list of users by name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<UserDto>> getByName(@PathVariable("firstName") String firstName) {

        List<User> users = service.getByName(firstName);
        List<UserDto> userDtos = users.stream()
                .map(mapper::userToUserDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userDtos);
    }

    @Operation(
            summary = "Get a list of users by last name",
            description = "Retrieves a list of users by last name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/last_name/{lastName}")
    public ResponseEntity<List<UserDto>> getByLastName(@PathVariable("lastName") String lastName) {

        List<User> users = service.getByLastName(lastName);
        List<UserDto> userDtos = users.stream()
                .map(mapper::userToUserDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userDtos);
    }

    @Operation(
            summary = "Get a list of users by users type",
            description = "Retrieves a list of users by type",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/type/{type}")
    public ResponseEntity<List<UserDto>> getByType(@PathVariable("type") String type) {

        List<User> users = service.getByType(type);
        List<UserDto> userDtos = users.stream()
                .map(mapper::userToUserDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(userDtos);
    }

    @Operation(
            summary = "Get user by email",
            description = "Retrieves a user by email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable("email") String email) {

        User user = service.getByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UserDto userDto = mapper.userToUserDto(user);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @Operation(
            summary = "Get a user by mobile phone number",
            description = "Retrieves a user by mobile phone number",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/mobile/{mobile}")
    public ResponseEntity<UserDto> getByMobile(@PathVariable("mobile") String mobile) {

        User user = service.getByMobile(mobile);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UserDto userDto = mapper.userToUserDto(user);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @Operation(
            summary = "Get user by pesel",
            description = "Retrieves a user by pesel number",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/pesel/{pesel}")
    public ResponseEntity<UserDto> getByPesel(@PathVariable("pesel") String pesel) {

        User user = service.getByPesel(pesel);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UserDto userDto = mapper.userToUserDto(user);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }


    @Operation(
            summary = "Delete a user by ID",
            description = "Deletes a user with the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted user"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {

        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Search users by dynamic criteria",
            description = "Retrieves a list of users based on dynamic search criteria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
                    @ApiResponse(responseCode = "404", description = "Users not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUsers(@RequestParam Map<String, String> searchParams) {
        List<User> users = service.searchUsers(searchParams);

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<UserDto> userDtos = users.stream()
                .map(mapper::userToUserDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(userDtos);
    }
}
