package com.softwaresmyth.fibonacci;

import java.math.BigInteger;

/**
 * Implementation based on naive function recursion. Do to the possibility of stack overflows, the
 * constructor takes an argument for maximum index allowed to calculate. Any calculations that would that index return null.
 *
 * Since this implementation does not memoize calculations, the worst case runtime complexity is absurd.
 */
public class NaiveRecursive implements FibonacciCalculator
{
    private final int maxDepth;

    public NaiveRecursive(int maxDepth)
    {
        this.maxDepth = maxDepth;
    }

    @Override
    public BigInteger fibbonacci(int n)
    {
        if (n > maxDepth)
        {
            return null;
        }

        if (n < 2)
        {
            return BigInteger.valueOf(n);
        }

        return fibbonacci(n - 1).add(fibbonacci(n - 2));
    }
}
