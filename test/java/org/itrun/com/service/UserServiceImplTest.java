package org.itrun.com.service;

import org.itrun.com.entity.User;
import org.itrun.com.exceptions.UserNotFoundException;
import org.itrun.com.repository.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Mock
    private UserJpaRepository repositoryMock;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {

        User user = new User();
        user.setFirstName("Kacper");
        user.setLastName("Nowak");
        user.setEmail("kacper@example.com");

        when(repositoryMock.save(any(User.class))).thenReturn(user);

        User createdUser = userService.create(user);

        verify(repositoryMock).save(any(User.class));

        assertEquals(user, createdUser);
    }

    @Test
    public void testGetAllUsers() {

        List<User> userList = new ArrayList<>();
        userList.add(new User("internal", "Kacper", "Nowak", "233-444-143", "kacper@example.com", "12345678901"));
        userList.add(new User("external", "Iga", "Swiatek", "912-000-143", "test@example.com", "09876543210"));

        when(repositoryMock.findAll()).thenReturn(userList);

        List<User> allUsers = userService.getAll();

        verify(repositoryMock).findAll();

        assertEquals(userList, allUsers);
    }

    @Test
    public void testEditUser() {

        User existingUser = new User("internal", "Kacper", "Nowak", "233-444-143", "kacper@example.com", "12345678901");
        existingUser.setId(1L);

        User updatedUser = new User("external", "Kacper", "Nowak", "663-444-143", "kacper@mail.com", "12345678901");

        when(repositoryMock.findById(1L)).thenReturn(java.util.Optional.of(existingUser));
        when(repositoryMock.save(any(User.class))).thenReturn(updatedUser);

        User editedUser = userService.edit(1L, updatedUser);

        verify(repositoryMock).findById(1L);
        verify(repositoryMock).save(any(User.class));

        assertEquals(updatedUser.getLastName(), editedUser.getLastName());
    }

    @Test
    public void testEditUserNotFound() {

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setFirstName("existingUser");
        existingUser.setEmail("existing@example.com");

        when(repositoryMock.findById(1L)).thenReturn(Optional.empty());

        User editedUser = new User();
        editedUser.setId(1L);
        editedUser.setFirstName("editedUser");
        editedUser.setEmail("edited@example.com");

        assertThrows(UserNotFoundException.class, () -> {
            userService.edit(1L, editedUser);
        });
    }
}