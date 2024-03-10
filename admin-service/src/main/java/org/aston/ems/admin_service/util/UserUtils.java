package org.aston.ems.admin_service.util;

import org.aston.ems.admin_service.model.User;

public class UserUtils {

    public static User update(User oldUser, User newUser) {
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setAuthorities(newUser.getAuthorities());
        return oldUser;
    }
}
