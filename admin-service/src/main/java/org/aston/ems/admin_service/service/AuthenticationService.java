package org.aston.ems.admin_service.service;

import org.aston.ems.admin_service.dto.JwtAuthenticationResponse;
import org.aston.ems.admin_service.model.User;

public interface AuthenticationService {

    /**
     * Аутентификация пользователя
     *
     * @param user данные пользователя
     * @return токен
     */
    JwtAuthenticationResponse signIn(User user);

    /**
     * Регистрация пользователя
     *
     * @param user данные пользователя
     * @return токен
     */
    JwtAuthenticationResponse signUp(User user);
}
