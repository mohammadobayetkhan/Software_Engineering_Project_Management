
package com.obayet.mathwhiz.service;

public class MathServiceTest {

    public static void main(String[] args) {

        System.out.println("===== MATH WHIZ TEST =====");

        System.out.println("Prime 7: "
                + MathService.isPrime(7));

        System.out.println("Prime 8: "
                + MathService.isPrime(8));

        System.out.println("Prime 1: "
                + MathService.isPrime(1));

        System.out.println("17 % 5: "
                + MathService.calculateMod(17, 5));

        System.out.println("Sin 30: "
                + MathService.calculateSin(30));

        System.out.println("Cos 60: "
                + MathService.calculateCos(60));

        System.out.println("Tan 45: "
                + MathService.calculateTan(45));

    }
}
