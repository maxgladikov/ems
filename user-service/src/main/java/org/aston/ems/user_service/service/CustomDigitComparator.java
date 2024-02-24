package org.aston.ems.user_service.service;


import org.aston.ems.user_service.dto.UserDTO;
import java.util.Comparator;

public class CustomDigitComparator implements Comparator<UserDTO> {
    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        return Integer.compare(o1.getMark() - o2.getMark(), 0);
    }
}
