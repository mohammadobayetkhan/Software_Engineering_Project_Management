
package com.obayet.mathwhiz.service;

public class MathService {

    // Check Prime Number
    public static int isPrime(int number) {

        if (number < 2) {
            return 0;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return 0;
            }
        }

        return 1;
    }

    // Calculate Modulus
    public static int calculateMod(int a, int b) {

        if (b == 0) {
            throw new IllegalArgumentException(
                    "Divisor cannot be zero"
            );
        }

        return a % b;
    }

    // Calculate Sine
    public static double calculateSin(double degree) {

        double radian = Math.toRadians(degree);

        return Math.sin(radian);
    }

    // Calculate Cosine
    public static double calculateCos(double degree) {

        double radian = Math.toRadians(degree);

        return Math.cos(radian);
    }

    // Calculate Tangent
    public static double calculateTan(double degree) {

        if (Math.abs(Math.cos(Math.toRadians(degree))) < 1e-10) {
            throw new IllegalArgumentException(
                    "Tangent is undefined at this angle"
            );
        }

        double radian = Math.toRadians(degree);

        return Math.tan(radian);
    }

}
