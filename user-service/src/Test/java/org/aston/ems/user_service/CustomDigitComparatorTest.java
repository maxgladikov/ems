package org.aston.ems.user_service;


import org.aston.ems.user_service.service.CustomDigitComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomDigitComparatorTest {

    CustomDigitComparator testCase;
    @BeforeEach
    void setUp() {
        testCase = new CustomDigitComparator();
    }

    @Test
    void compareNaturelOrder() {

        Assertions.assertEquals(-1, testCase.compare(1, 2));
        Assertions.assertEquals(1, testCase.compare(2, 1));
        Assertions.assertEquals(0, testCase.compare(2, 2));
    }

    @Test
    void compareReverseOrder() {

        Assertions.assertEquals(1, testCase.compareReverse(1, 2));
        Assertions.assertEquals(-1, testCase.compareReverse(2, 1));
        Assertions.assertEquals(0, testCase.compareReverse(2, 2));
    }


}