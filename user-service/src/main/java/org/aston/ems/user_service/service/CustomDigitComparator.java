package org.aston.ems.user_service.service;

import org.aston.ems.user_service.controller.HelloController;

import java.util.Comparator;

public class CustomDigitComparator  implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
