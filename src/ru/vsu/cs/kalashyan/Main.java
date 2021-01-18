package ru.vsu.cs.kalashyan;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double x = readVariable("x");
        double n = readVariable("n");
        double e = readVariable("e");

        if (!verifyVariableX(x)) {
            return;
        }

        double function = calculateFunction(x);
        double sumOfNTerm = calculateSumOfNTerm(x, (int) n);
        Conclusion resultEpsSum = calculateEpsSum(x, e);
        Conclusion resultEpsSumOn10 = calculateEpsSum(x, e / 10);
        printResult(sumOfNTerm, resultEpsSum, resultEpsSumOn10, function);
    }

    public static double calculateSumOfNTerm(double x, int n) {
        double r = 1;
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += r;
            r = -r * x * (i + 1) / i;
        }
        return sum;
    }

    public static Conclusion calculateEpsSum(double x, double e) {
        double r = 1;
        double sum = 0;
        int n = 0;
        while (Math.abs(sum - (sum + r)) > e) {
            sum += r;
            n++;
            r = -r * x * (n + 1) / n;
        }
        return new Conclusion(n, sum);
    }

    public static double calculateFunction(double x) {
        return 1 / Math.pow(1 + x, 2);
    }

    public static double readVariable(String string) {
        Scanner scn = new Scanner(System.in);
        System.out.printf("Введите %s: ", string);
        return scn.nextDouble();
    }

    public static boolean verifyVariableX(double x) {
        if (x < -1 || x > 1) {
            System.out.println("x does not belong to the interval (-1;1)");
            return false;
        }
        return true;
    }

    public static void printResult(double sumOfNTerm, Conclusion resultEpsSum, Conclusion resultEpsSumOn10, double function) {
        System.out.printf("1) The value of the function is equal to %.5f\n", sumOfNTerm);
        System.out.printf("2) The value of the function at %d the terms are equal to %.5f\n", resultEpsSum.n, resultEpsSum.sum);
        System.out.printf("3) The value of the function at %d the terms are equal to %.5f\n", resultEpsSumOn10.n, resultEpsSumOn10.sum);
        System.out.printf("4) The value of the function 1/(1+x)^2 equal to %.5f\n", function);
    }
}
