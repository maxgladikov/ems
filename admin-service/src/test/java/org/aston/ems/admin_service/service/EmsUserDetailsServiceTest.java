package org.aston.ems.admin_service.service;

import lombok.RequiredArgsConstructor;
import org.aston.ems.admin_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.aston.ems.admin_service.TestFactory.USER;
import static org.aston.ems.admin_service.TestFactory.USER_DETAILS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = UserDetailsServiceImpl.class)
@RequiredArgsConstructor
class EmsUserDetailsServiceTest {

    private final UserDetailsServiceImpl service;

    @MockBean
    private UserRepository repository;

    @Test
    void shouldReturnUser() {
        String givenName = "test";

        when(repository.findByUsername(givenName)).thenReturn(Optional.of(USER));
        assertEquals(USER_DETAILS,service.loadUserByUsername(givenName));
        verify(repository,times(1)).findByUsername(givenName);
    }
}