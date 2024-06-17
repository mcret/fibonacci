package com.softwaresmyth.fibonacci;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibTest
{
    final static Map<Integer, BigInteger> expectedValues = new HashMap<>();

    static
    {
        expectedValues.put(0, BigInteger.valueOf(0));
        expectedValues.put(1, BigInteger.valueOf(1));
        expectedValues.put(2, BigInteger.valueOf(1));
        expectedValues.put(3, BigInteger.valueOf(2));
        expectedValues.put(4, BigInteger.valueOf(3));
        expectedValues.put(5, BigInteger.valueOf(5));
        expectedValues.put(6, BigInteger.valueOf(8));
        expectedValues.put(7, BigInteger.valueOf(13));
        expectedValues.put(8, BigInteger.valueOf(21));
        expectedValues.put(9, BigInteger.valueOf(34));
        expectedValues.put(10, BigInteger.valueOf(55));
    }

    public static Stream<Arguments> testFibonacci()
    {
        return Stream.of(new FibByMatrix(),
                         new FibByAddLoop(),
                         new FibByPhi(),
                         new NaiveRecursive(10))
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource
    void testFibonacci(FibonacciCalculator calculator)
    {
        expectedValues.entrySet().stream()
                .forEach(entry -> assertEquals(entry.getValue(),
                                               calculator.fibbonacci(entry.getKey()),
                                               "Expected fibonacci value of " + entry.getValue()));
    }
}