//package org.aston.ems.admin_service;
//
//import org.junit.jupiter.api.DynamicTest;
//import org.junit.jupiter.api.TestFactory;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.stream.IntStream;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class Factory {
//
//    @TestFactory
//    Collection<DynamicTest> dynamicTestsWithCollection() {
//        return Arrays.asList(
//            DynamicTest.dynamicTest("Add test",
//                () -> assertEquals(2, Math.addExact(1, 1))),
//            DynamicTest.dynamicTest("Multiply Test",
//                () -> assertEquals(4, Math.multiplyExact(2, 2))));
//    }
//
//    @TestFactory
//    Stream<DynamicTest> dynamicTestsFromIntStream() {
//        return IntStream.iterate(0, n -> n + 2).limit(10)
//            .mapToObj(n -> DynamicTest.dynamicTest("test" + n,
//                () -> assertTrue(n % 2 == 0)));
//    }
//
//}
