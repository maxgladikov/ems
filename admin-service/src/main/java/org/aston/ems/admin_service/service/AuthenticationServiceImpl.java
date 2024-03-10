package org.aston.ems.admin_service.service;

import lombok.RequiredArgsConstructor;
import org.aston.ems.admin_service.dto.JwtAuthenticationResponse;
import org.aston.ems.admin_service.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signIn(User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            user.getPassword()
        ));

        return new JwtAuthenticationResponse(jwtService.generateToken(user));
    }

    @Override
    public JwtAuthenticationResponse signUp(User user) {
        userService.create(user);
        return new JwtAuthenticationResponse(jwtService.generateToken(user));
    }
}
