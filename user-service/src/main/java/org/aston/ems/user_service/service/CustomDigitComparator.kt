package org.aston.ems.user_service.service

import org.aston.ems.user_service.dto.UserDTO
import org.springframework.stereotype.Service


@Service
class CustomDigitComparator : Comparator<UserDTO> {
    override fun compare(o1: UserDTO, o2: UserDTO): Int {
        return 0.compareTo(o2.mark - o1.mark)
    }
}
