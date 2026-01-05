/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inputter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Inputter {

    private static final Scanner sc = new Scanner(System.in);

    //Dung de nhap DateTime
    public static LocalDateTime inputDateTime(String prompt) {
        while (true) {
            System.out.println(prompt + "YYYY-MM-DD HH:MM");
            String code = sc.nextLine().trim();
            if (code.isEmpty()) {
                return null;
            } else {
                try {
                    return LocalDateTime.parse(code, Validation.DATETIME_FMT);
                } catch (Exception e) {
                    System.out.println("Invalid datetime! Example : 2025-10-17 11:00");
                }
            }
        }
    }

    //Dung de nhap 1 chuoi(cho phep null)
    public static String inputStringAllowEmpty(String prompt, String pattern) {
        System.out.println(prompt);
        while (true) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return "";
            }
            try {
                if (input.matches(pattern)) {
                    return input;
                }
            } catch (Exception e) {
                System.out.println("Invalid format! Please try it again: ");
            }
        }
    }

    //Dung de nhap 1 so nguyen (cho phep null)
    public static Integer inputIntegerAllowEmpty(String prompt) {
        while (true) {
            System.out.println(prompt);
            String s = sc.nextLine();
            if (s.isEmpty()) {
                return null;
            }
            try {
                int v = Integer.parseInt(s);
                if (v > 0) {
                    return v;
                } else {
                    System.out.println("Value must be > 0");
                }
            } catch (Exception e) {
                System.out.println("You must enter a positive integer!");
            }
        }
    }

    public static String inputString(String prompt, String pattern) {
        System.out.println(prompt);
        while (true) {
            String input = sc.nextLine().trim();
            if (input.matches(pattern)) {
                return input;
            } else {
                System.out.println("Invalid input! Please try again!");
            }
        }
    }

    public static LocalDate inputDate(String prompt) {
        while (true) {
            System.out.println(prompt);
            String s = sc.nextLine().trim();
            if (s.matches(Validation.DATE_REGEX)) {
                try {
                    return LocalDate.parse(s, Validation.DATE_FMT);
                } catch (Exception e) {
                    System.out.println("Invalid date!!! Example: 2025-11-03");
                }
            } else {
                System.out.println("Invalid format!!! Example : 2025-11-03");
            }
        }
    }

    public static LocalTime inputTime(String prompt) {
        while (true) {
            System.out.println(prompt);
            String s = sc.nextLine().trim();
            if (s.matches(Validation.TIME_REGEX)) {
                try {
                    return LocalTime.parse(s, Validation.TIME_FMT);
                } catch (Exception e) {
                    System.out.println("Invalid time!! Example: 7:00 or 19:30");
                }
            } else {
                System.out.println("Invalid format!!! Example : 7:00 or 19:30");
            }
        }
    }

    public static LocalDate inputDateAllowEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = sc.nextLine().trim();

            if (raw.isEmpty()) {
                return LocalDate.now();
            }

            try {
                return LocalDate.parse(raw, Validation.DATE_FMT);
            } catch (Exception e) {
                System.out.println("Invalid date format (YYYY-MM-DD). Please try again.");
            }
        }
    }
}
