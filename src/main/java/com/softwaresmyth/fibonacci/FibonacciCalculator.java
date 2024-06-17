package com.softwaresmyth.fibonacci;

import java.math.BigInteger;

/**
 * Interface for calculating the n-th number in the Fibonacci sequence.
 */
public interface FibonacciCalculator
{
    /**
     * Calculates and returns the zero-indexed n-th Fibonacci number, ie getFibonacci(0) == 0 and getFibonacci(5) == 5
     * @return The n-th Fibonacci number. BigInteger is used since fibbonacci(93) == 12200160415121876738 > Long.MAX_VALUE
     */
    BigInteger fibbonacci(int n);
}
