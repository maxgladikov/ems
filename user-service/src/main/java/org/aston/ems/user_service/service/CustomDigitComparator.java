package org.aston.ems.user_service.service;


import org.aston.ems.user_service.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class CustomDigitComparator implements Comparator<UserDTO> {
    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        return Integer.compare(o1.getMark() - o2.getMark(), 0);
    }
}
