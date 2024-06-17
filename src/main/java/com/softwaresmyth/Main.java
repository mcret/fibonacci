package com.softwaresmyth;

import com.softwaresmyth.fibonacci.FibByAddLoop;
import com.softwaresmyth.fibonacci.FibByMatrix;
import com.softwaresmyth.fibonacci.FibonacciCalculator;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<FibonacciCalculator> calculators = new ArrayList<>();
        calculators.add(new FibByMatrix());
        calculators.add(new FibByAddLoop());
        compareForValue(10, 5, calculators);

        FibByMatrix fibByMatrix = new FibByMatrix();
        BigInteger maxLong = BigInteger.valueOf(Long.MAX_VALUE);
        int n = 50;
        while(n < 1000000000)
        {
            if (maxLong.compareTo(fibByMatrix.fibbonacci(n)) < 0)
            {
                break;
            }
            n++;
        }

        System.out.printf("First index with a value greater than Long.MAX_VALUE is %d\n", n);
        System.out.println(fibByMatrix.fibbonacci(n));
    }

    /**
     * WIP: Find the first index for which the matrix implementation is faster than the add-loop implementation.
     *
     * Not getting a good output from this yet. Not sure if the implementation is bad, or if the JVM is just that noisy.
     * @param min minimum index to try
     * @param max maximum index to try
     * @param loops Iterations to run per index
     * @return The first index for which the matrix implementation is faster
     */
    static int findCrossoverPoint(int min, int max, int loops)
    {
        Duration loopDuration;
        Duration matrixDuration;

        System.out.println("Seek algorithm crossover point.");

        FibByAddLoop fibByAddLoop = new FibByAddLoop();
        FibByMatrix fibByMatrix = new FibByMatrix();

        int n = min;

        do
        {
            loopDuration = runManyTimes(n, loops, fibByAddLoop);
            matrixDuration = runManyTimes(n, loops, fibByMatrix);
            n++;
        }
        while (n < max && loopDuration.compareTo(matrixDuration) < 0);

        System.out.printf("First n with Matrix method being faster: %s%n", n - 1);

        return n - 1;
    }

    /**
     * Times how long the given implementation takes to calculate Fibonacci(n) many times
     * @param n Index of the Fibonacci sequence to calculate
     * @param times Number of times to calculate the number
     * @param calculator Implementation of the calculator to use
     * @return What the total duration for the calculations was.
     */
    static Duration runManyTimes(int n, int times, FibonacciCalculator calculator)
    {
        Instant startTime = Instant.now();
        for (int i = 0; i < times; i++)
        {
            calculator.fibbonacci(n);
        }
        Instant endTime = Instant.now();
        return Duration.between(startTime, endTime);
    }

    /**
     * Runs a list of implementations for a specific index and a given number of iterations.
     * @param n The index to calculate
     * @param times How many iterations to run
     */
    static void compareForValue(int n, int times, List<FibonacciCalculator> calculators)
    {
        System.out.printf("n = %s%n", n);
        System.out.printf("runs = %s%n", times);

        for (FibonacciCalculator calculator : calculators)
        {
            runForAverage(n, times, calculator);
        }
    }

    /**
     * Calculates a given index a given number of times against a given implementation. Prints total and average calculation durations.
     * @param n Index to calculate
     * @param times Number of iterations
     * @param calculator Implementation to run
     * @return Total duration
     */
    static Duration runForAverage(int n, int times, FibonacciCalculator calculator)
    {
        Duration duration = runManyTimes(n, times, calculator);
        String className = calculator.getClass().getSimpleName();
        System.out.printf("%s: %s%n", className, prettify(duration));
        System.out.printf("%s average: %s%n", className, prettify(duration.dividedBy(times)));
        return duration;
    }

    /**
     * Converts a Duration to a human-readable string
     */
    public static String prettify(Duration duration)
    {
        return duration.toString()
                .substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .toLowerCase();
    }
}