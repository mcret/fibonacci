package com.softwaresmyth.fibonacci;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * WIP: Implementation based on the golden ratio. This method is mathematically sound, but suffers from floating point
 * rounding errors at sufficiently high values of n.
 *
 * Like the matrix exponentiation, the runtime complexity is dominated by the exponentiation. Presumably BigDecimal's
 * implementation of pow is logarithmic, but I have not checked.
 */
public class FibByPhi implements FibonacciCalculator
{
    private static final double rootFive = Math.sqrt(5);
    private static final double phi = 0.5 * (rootFive + 1);

    private static final BigDecimal bigRootFive = new BigDecimal(rootFive);
    private static final BigDecimal bigPhi = new BigDecimal(phi);

    @Override
    public BigInteger fibbonacci(int n)
    {
        if (n < 2)
        {
            return BigInteger.valueOf(n);
        }

        BigDecimal result = bigPhi.pow(n)
                .divide(bigRootFive, MathContext.DECIMAL128)
                .round(new MathContext(1, RoundingMode.HALF_UP));

        return new BigInteger(result.toPlainString());
    }
}
