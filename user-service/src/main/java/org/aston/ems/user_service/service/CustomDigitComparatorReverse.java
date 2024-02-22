package org.aston.ems.user_service.service;

import java.util.Comparator;

public class CustomDigitComparatorReverse implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }
}
