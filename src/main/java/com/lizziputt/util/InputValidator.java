package com.lizziputt.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputValidator {
    public static final LocalDateTime EXIT_TIME = LocalDateTime.MAX;

    public static int validateId() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                String in = scan.nextLine();
                if ("exit".equalsIgnoreCase(in)) return 0;
                return Integer.parseInt(in);
            } catch (NumberFormatException e) {
                System.out.print("Invalid id! Only Number are allowed! Please try again!\n> ");
            }
        }
    }

    public static List<Integer> validateIds() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                String in = scan.nextLine();
                if ("exit".equalsIgnoreCase(in)) return List.of();
                return Arrays.stream(in.split(" ")).map(Integer::parseInt).toList();
            } catch (NumberFormatException e) {
                System.out.print("Invalid id! Only Number are allowed! Please try again!\n> ");
            }
        }
    }

    public static LocalDateTime validateTime() {
        System.out.print("Enter time of the class in format: hh:mm:ss\n> ");
        int[] t = getTime();
        if (t.length == 0) return EXIT_TIME;

        System.out.print("Enter date of the class in format: day.month.year\n> ");
        int[] d = getDate();
        if (d.length == 0) return EXIT_TIME;

        return LocalDateTime.of(LocalDate.of(d[2], d[1], d[0]), LocalTime.of(t[0], t[1], t[2]));
    }

    private static int[] getDate() {
        return validDate("\\.", "Wrong date pattern! Should be day.month.year! Example: 15.08.2003");
    }

    private static int[] getTime() {
        return validDate(":", "Wrong time pattern! Should be hh:mm:ss! Example: 08:30. Seconds could be omitted");
    }

    private static int[] validDate(String regex, String errorMessage) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                String in = scan.nextLine();
                if ("exit".equalsIgnoreCase(in)) return new int[]{};
                return Arrays.stream(in.split(regex)).mapToInt(Integer::parseInt).toArray();
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }
}