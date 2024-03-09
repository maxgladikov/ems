package org.aston.ems.admin_service.service;

import org.aston.ems.admin_service.dto.UserDto;
import org.aston.ems.admin_service.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    User get(String name);

    List<User> get();
    void create(User user);

    void update(User user);

    void delete(String username);
}
