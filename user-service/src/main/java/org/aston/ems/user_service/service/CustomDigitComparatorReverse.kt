package org.aston.ems.user_service.service

import org.aston.ems.user_service.dto.UserDTO
import org.springframework.stereotype.Service

@Service
class CustomDigitComparatorReverse : Comparator<UserDTO> {
    override fun compare(o1: UserDTO, o2: UserDTO): Int {
        return 0.compareTo(o1.mark- o2.mark)
    }
}
