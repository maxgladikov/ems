package org.aston.ems.user_service.service;


import org.aston.ems.user_service.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class RatingStudentServiceTest {
    RatingStudentService ratingStudentService = Mockito.mock(RatingStudentService.class);
    CustomDigitComparator naturalComparatorCase;
    CustomDigitComparatorReverse reverseComparatorCase;

    List<UserDTO> listDTONaturelOrder, listDTOReverseOrder, listDTO;
    @BeforeEach
    void setUp() {
        naturalComparatorCase = new CustomDigitComparator();
        reverseComparatorCase = new CustomDigitComparatorReverse();

        listDTONaturelOrder = new ArrayList<>();
        listDTONaturelOrder.add(new UserDTO("name5", 1));
        listDTONaturelOrder.add(new UserDTO("name2", 8));
        listDTONaturelOrder.add(new UserDTO("name3", 10));
        listDTONaturelOrder.add(new UserDTO("name4", 15));
        listDTONaturelOrder.add(new UserDTO("name1", 20));

        listDTOReverseOrder = new ArrayList<>();
        listDTOReverseOrder.add(new UserDTO("name1", 20));
        listDTOReverseOrder.add(new UserDTO("name4", 15));
        listDTOReverseOrder.add(new UserDTO("name3", 10));
        listDTOReverseOrder.add(new UserDTO("name2", 8));
        listDTOReverseOrder.add(new UserDTO("name5", 1));

        listDTO = new ArrayList<>();
        listDTO.add(new UserDTO("name1", 20));
        listDTO.add(new UserDTO("name2", 8));
        listDTO.add(new UserDTO("name3", 10));
        listDTO.add(new UserDTO("name4", 15));
        listDTO.add(new UserDTO("name5", 1));
    }

    @Test
    void getRatingStudent(){
    }
}