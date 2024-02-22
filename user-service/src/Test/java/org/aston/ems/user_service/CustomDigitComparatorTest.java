package org.aston.ems.user_service;


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
    List<Integer> list;
    @BeforeEach
    void setUp() {
        naturalComparatorCase = new CustomDigitComparator();
        reverseComparatorCase = new CustomDigitComparatorReverse();
        list = new ArrayList<>();
        list.add(2);
        list.add(4);
        list.add(1);
        list.add(3);
    }

    @Test
    void compareNaturelOrder() {

        list.sort(naturalComparatorCase);

        List<Integer> testArray = new ArrayList<>();
        testArray.add(1);
        testArray.add(2);
        testArray.add(3);
        testArray.add(4);

        assertEquals(testArray, list);

        assertEquals(-1, naturalComparatorCase.compare(1, 2));
        assertEquals(1, naturalComparatorCase.compare(2, 1));
        assertEquals(0, naturalComparatorCase.compare(2, 2));
    }

    @Test
    void compareReverseOrder() {

        list.sort(reverseComparatorCase);

        List<Integer> testArray = new ArrayList<>();
        testArray.add(4);
        testArray.add(3);
        testArray.add(2);
        testArray.add(1);

        assertEquals(testArray, list);

        assertEquals(1, reverseComparatorCase.compare(1, 2));
        assertEquals(-1, reverseComparatorCase.compare(2, 1));
        assertEquals(0, reverseComparatorCase.compare(2, 2));
    }


}