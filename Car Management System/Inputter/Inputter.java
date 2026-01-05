/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inputter;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Inputter {

    private static final Scanner sc = new Scanner(System.in);

    public static String inputString(String prompt, String pattern) {
        System.out.println(prompt);
        while (true) {
            String input = sc.nextLine().trim();
            if (input.matches(pattern)) {
                return input;
            }
            System.out.println("Invalid format! Please try it again: ");
        }
    }

    public static Double inputDouble(String prompt) {
        while (true) {
            System.out.println(prompt);
            String s = sc.nextLine();
            try {
                double v = Double.parseDouble(s);
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

    public static Double inputDoubleAllowEmpty(String prompt) {
        while (true) {
            System.out.println(prompt);
            String s = sc.nextLine();
            if (s.isEmpty()) {
                return null;
            }
            try {
                double v = Double.parseDouble(s);
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
}
