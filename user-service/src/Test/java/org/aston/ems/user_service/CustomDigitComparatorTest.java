package org.aston.ems.user_service;


import org.aston.ems.user_service.dto.UserDTO;
import org.aston.ems.user_service.service.CustomDigitComparator;
import org.aston.ems.user_service.service.CustomDigitComparatorReverse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomDigitComparatorTest {

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
    void compareNaturelOrder() {

        listDTO.sort(naturalComparatorCase);
        System.out.println("listDTONaturelOrder UserDTO=" + listDTO);

        assertEquals(listDTONaturelOrder, listDTO);

        assertEquals(1, naturalComparatorCase.compare(new UserDTO("name1", 20), new UserDTO("name2", 8)));
        assertEquals(-1, naturalComparatorCase.compare(new UserDTO("name2", 8), new UserDTO("name1", 20)));
        assertEquals(0, naturalComparatorCase.compare(new UserDTO("name2", 8), new UserDTO("name2", 8)));
}

@Test
    void compareReverseOrder() {

        listDTO.sort(reverseComparatorCase);
        System.out.println("listDTOReverseOrder UserDTO=" + listDTO);

        assertEquals(listDTOReverseOrder, listDTO);

        assertEquals(-1, reverseComparatorCase.compare(new UserDTO("name1", 20), new UserDTO("name2", 8)));
        assertEquals(1, reverseComparatorCase.compare(new UserDTO("name2", 8), new UserDTO("name1", 20)));
        assertEquals(0, reverseComparatorCase.compare(new UserDTO("name2", 8), new UserDTO("name2", 8)));
    }


}