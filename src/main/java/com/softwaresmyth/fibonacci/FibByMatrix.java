package com.softwaresmyth.fibonacci;

import java.math.BigInteger;

/**
 * Implementation with pure integer arithmetic, based on matrix exponentiation.
 *
 * Runtime complexity is logarithmic, dominated by the matrix exponentiation (here implemented by the square-multiply
 * algorithm), with 8 multiplications and 4 additions per loop.
 */
public class FibByMatrix implements FibonacciCalculator
{
    private static final Matrix base = new Matrix(1, 1, 1, 0);

    @Override
    public BigInteger fibbonacci(int n)
    {
        if (n < 2)
        {
            return BigInteger.valueOf(n);
        }

        BigInteger exponent = BigInteger.valueOf(n - 1);

        return base.pow(exponent).a;
    }

    private static class Matrix
    {
        final BigInteger a;
        final BigInteger b;
        final BigInteger c;
        final BigInteger d;

        public Matrix(int a, int b, int c, int d)
        {
            this(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(d));
        }

        public Matrix(BigInteger a, BigInteger b, BigInteger c, BigInteger d)
        {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public Matrix times(Matrix other)
        {
            BigInteger a = this.a.multiply(other.a).add(this.b.multiply(other.c));
            BigInteger b = this.a.multiply(other.b).add(this.b.multiply(other.d));
            BigInteger c = this.c.multiply(other.a).add(this.d.multiply(other.c));
            BigInteger d = this.c.multiply(other.b).add(this.d.multiply(other.d));

            return new Matrix(a, b, c, d);
        }

        public Matrix pow(BigInteger exponent)
        {
            String binary = exponent.toString(2);

            Matrix base = this;
            Matrix result = this;

            char[] charArray = binary.toCharArray();
            for (int i = 1; i < charArray.length; i++)
            {
                result = result.times(result);
                if ('1' == charArray[i])
                {
                    result = result.times(base);
                }
            }

            return result;
        }
    }
}
