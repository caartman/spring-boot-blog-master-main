package com.bekov;

import com.bekov.model.User;
import com.bekov.model.Role; // Import the Role class
import com.bekov.repository.RoleRepository;
import com.bekov.repository.UserRepository;
import com.bekov.service.impl.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImpTest {
    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    private UserServiceImp userServiceImp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userServiceImp = new UserServiceImp(mockUserRepository, mockRoleRepository, mockPasswordEncoder);
    }

    @Test
    public void testFindByUsername() {
        String username = "user";
        User user = new User();
        when(mockUserRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> result = userServiceImp.findByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(mockUserRepository).findByUsername(username);
    }

    @Test
    public void testFindByEmail() {
        String email = "john.doe@example.com";
        User user = new User();
        when(mockUserRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = userServiceImp.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(mockUserRepository).findByEmail(email);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setPassword("plaintextPassword"); // Set a non-null password

        String encodedPassword = "password";
        when(mockPasswordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
        when(mockRoleRepository.findByRole("ROLE_USER")).thenReturn(mock(Role.class));
        when(mockUserRepository.saveAndFlush(user)).thenReturn(user);

        User result = userServiceImp.save(user);

        assertEquals(encodedPassword, result.getPassword());
        assertEquals(1, result.getActive());
        assertEquals(Collections.singletonList(mock(Role.class)), result.getRoles());
        verify(mockPasswordEncoder).encode(user.getPassword());
        verify(mockRoleRepository).findByRole("ROLE_USER");
        verify(mockUserRepository).saveAndFlush(user);
    }



}
