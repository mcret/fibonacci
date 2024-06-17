package com.softwaresmyth.fibonacci;

import java.math.BigInteger;

/**
 * Implementation by repeated addition in a tight loop.
 *
 * Runtime complexity is linear, with one addition per loop.
 */
public class FibByAddLoop implements FibonacciCalculator
{
    @Override
    public BigInteger fibbonacci(int n)
    {
        if (n < 2)
        {
            return BigInteger.valueOf(n);
        }

        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ZERO;
        BigInteger c;

        for (int i = 0; i < n - 1; i++)
        {
            c = a;
            a = a.add(b);
            b = c;
        }

        return a;
    }
}
