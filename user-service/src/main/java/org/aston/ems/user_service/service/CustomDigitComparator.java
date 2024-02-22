package org.aston.ems.user_service.service;

import java.util.Comparator;

public class CustomDigitComparator  implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }

    public int compareReverse(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }
}
