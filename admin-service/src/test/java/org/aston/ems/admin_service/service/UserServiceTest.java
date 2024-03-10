package org.aston.ems.admin_service.service;

import org.aston.ems.admin_service.model.Authority;
import org.aston.ems.admin_service.repository.AuthorityRepository;
import org.aston.ems.admin_service.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.aston.ems.admin_service.TestFactory.USER;
import static org.aston.ems.admin_service.TestFactory.USERS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Disabled
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityRepository authorityRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void getAll_shouldReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(USERS);
        assertEquals(USERS,service.get());
        verify(userRepository,times(1)).findAll();
    }

    @Test
    void getByName_shouldReturnUser() {
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(USER));
        assertEquals(USER,service.get("someName"));
        verify(userRepository,times(1)).findByUsername(any());
    }

    @Test
    @Disabled
    void create_shouldCreateUser() {
        when(encoder.encode(any())).thenReturn("encoded");
        when(authorityRepository.findByName("ADMIN")).thenReturn(Optional.of(new Authority("ADMIN")));
        when(authorityRepository.findByName("USER")).thenReturn(Optional.of(new Authority("USER")));
        when(userRepository.save(any())).thenReturn(USER);
        service.create(USER);
        verify(userRepository,times(1)).save(any());
        verify(authorityRepository,times(2)).findByName(any());
        verify(encoder,times(1)).encode(any());
    }

    @Test
    @Disabled
    void update_shouldUpdateUser() {
        when(encoder.encode(any())).thenReturn("encoded");
        when(authorityRepository.findByName("ADMIN")).thenReturn(Optional.of(new Authority("ADMIN")));
        when(authorityRepository.findByName("USER")).thenReturn(Optional.of(new Authority("USER")));
        when(userRepository.save(any())).thenReturn(USER);
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(USER));
        service.update(USER);
        verify(userRepository,times(1)).save(any());
        verify(userRepository,times(1)).findByUsername(any());
        verify(authorityRepository,times(2)).findByName(any());
        verify(encoder,times(1)).encode(any());
    }

    @Test
    void delete_shouldDeleteUser() {
       doNothing().when(userRepository).deleteByUsername(any());
        service.delete("name");
        verify(userRepository,times(1)).deleteByUsername(any());
    }
}