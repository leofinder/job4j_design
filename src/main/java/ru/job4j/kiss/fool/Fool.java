package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(getCheckWord(startAt));
            startAt++;
            var answer = input.nextLine();
            startAt = calculateFizzBuzz(startAt, answer);
        }
    }

    public static int calculateFizzBuzz(int number, String answer) {
        String check = getCheckWord(number);
        return isCorrect(answer, check) ? number + 1 : 0;
    }

    public static String getCheckWord(int number) {
        String check = String.valueOf(number);
        if (number % 3 == 0 && number % 5 == 0) {
            check = "FizzBuzz";
        } else if (number % 3 == 0) {
            check = "Fizz";
        } else if (number % 5 == 0) {
            check = "Buzz";
        }
        return check;
    }

    public static boolean isCorrect(String answer, String check) {
        boolean result = check.equals(answer);
        if (!result) {
            System.out.println("Ошибка. Начинай снова.");
        }
        return result;
    }
}