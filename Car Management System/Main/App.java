/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Inputter.Validation;
import Manager.BrandList;
import Manager.CarList;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class App {

    public static void main(String[] args) {
        BrandList b = new BrandList();
        CarList c = new CarList(b);
        Scanner sc = new Scanner(System.in);
        b.readDataFromFile("src/Data/brands.txt");
        c.readDataFromFile("D:\\FPTU\\FALL25\\Lab211\\Project03\\src\\Data\\cars.txt");

        //Condition
        boolean isExit = false;
        boolean isSaved = false;

        do {
            System.out.println("\n/----------CAR SHOWROOM MANAGEMENT----------/");
            System.out.println("1- List all brands");
            System.out.println("2- Add a new brand");
            System.out.println("3- Search for a brand by ID");
            System.out.println("4- Update a brand by ID");
            System.out.println("5- List all brands with prices less than or equal to an input value");
            System.out.println("6- List all cars in ascending order of brand names");
            System.out.println("7- Search cars by partial brand name match");
            System.out.println("8- Display car");
            System.out.println("9- Add a new car");
            System.out.println("10- Remove a car by ID");
            System.out.println("11- Update a car by ID");
            System.out.println("12- List all cars by a specific color");
            System.out.println("13- Save data to files");
            System.out.println("14- Display brand not contain any car");
            System.out.println("15- Quit program");
            System.out.print("Please enter your choice: ");
            int choice;
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    b.displayBrand();
                    break;
                case 2:
                    b.addBrand();
                    isSaved = false;
                    break;
                case 3:
                    String brandID3 = Inputter.Inputter.inputString("Enter brand ID you want to search: ", Validation.BRAND_ID_FMT);
                    b.searchBrandByID(brandID3);
                    break;
                case 4:
                    String brandID4 = Inputter.Inputter.inputString("Enter brand ID you want to update: ", Validation.BRAND_ID_FMT);
                    b.updateBrandByID(brandID4);
                    isSaved = false;
                    break;
                case 5:
                    double price = Inputter.Inputter.inputDouble("Enter price you want to sort: ");
                    b.listBrandByPrice(price);
                    break;
                case 6:
                    c.listCarsByBrandName();
                    break;
                case 7:
                    System.out.println("Enter your partial brand name to search car: ");
                    String parBrandName = sc.nextLine();
                    c.searchCarsByBrandName(parBrandName);
                    break;
                case 8:
                    c.displayCar();
                    break;
                case 9:
                    c.addCar();
                    isSaved = false;
                    break;
                case 10:
                    c.removeCarByID();
                    isSaved = false;
                    break;
                case 11:
                    String carID = Inputter.Inputter.inputString("Enter car's ID to update: ", Validation.CAR_ID_FMT);
                    c.updateCarByID(carID);
                    isSaved = false;
                    break;
                case 12:
                    String carColor = Inputter.Inputter.inputString("Enter car's color to display: ", Validation.CAR_COLOR_FMT);
                    c.listCarByColor(carColor);
                    break;
                case 13:
                    b.saveToFile("Brands.txt");
                    c.saveToFile("Cars.txt");
                    System.out.println("Both car and brand have been saved successfully!");
                    isSaved = true;
                    break;
                case 14:
                    c.displayBrandDontHaveCar();
                    break;
                case 15:
                    if (!isSaved) {
                        System.out.println("Do you want to save the change before exiting (Y/N)? ");
                        String reply = sc.nextLine();

                        if (reply.equalsIgnoreCase("Y")) {
                            b.saveToFile("Brands.txt");
                            c.saveToFile("Cars.txt");
                            System.out.println("Both car and brand have been saved successfully!");
                            isSaved = true;
                            isExit = true;
                            System.out.println("Exit");
                            break;
                        } else {
                            isExit = true;
                            isSaved = false;

                            System.out.println("Exit without saved changes");
                            break;
                        }
                    }
                    isExit = true;
                    System.out.println("Bye");
                    break;
            }
        } while (!isExit);
    }
}
