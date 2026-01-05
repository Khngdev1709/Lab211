package View;

import Manager.BookingList;
import Manager.FacilityList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user
 */
public class Main {

    public static void main(String[] args) {
        FacilityList ds_san = new FacilityList();
        BookingList ds_booking = new BookingList();
        Scanner sc = new Scanner(System.in);

        //Condition
        boolean isExit = false;
        boolean isSaved = false;

        do {
            System.out.println("");
            System.out.println("=============== SPORT MANAGEMENT ===============");
            System.out.println("1. Import facility from CSV file");
            System.out.println("2. Update facility information");
            System.out.println("3. View facility information");
            System.out.println("4. Book a Facility");
            System.out.println("5. View today's booking");
            System.out.println("6. Cancel a booking");
            System.out.println("9. Save all data");
            System.out.println("10. Quit");

            int choice;
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    ds_san.LoadFacilityFromFile("D:\\FPTU\\FALL25\\Lab211\\Project02\\src\\Data\\Facility.txt");
                    ds_san.loadUnitpriceFromFile("D:\\FPTU\\FALL25\\Lab211\\Project02\\src\\Data\\UnitPrice.txt");
                    break;
                case 2:
                    ds_san.updateFacility();
                    isSaved = false;
                    break;
                case 3:
                    ds_san.displayFacility();
                    break;
                case 4:
                    ds_booking.createBooking(ds_san);
                    break;
                case 5:
                    ds_booking.viewTodayBooking();
                    break;
                case 6:
                    ds_booking.removeBooking();
                    break;
                case 9:
                    ds_san.saveToFile("FacilityList");
                    ds_san.saveUnitpriceToFile("UnitPrice");
                    ds_booking.saveToFile("Booking");
                    System.out.println("Both facility list and booking list have been saved successfully!");
                    isSaved = true;
                    break;
                case 10:
                    if (!isSaved) {
                        System.out.println("Do you want to save the changes before exiting? (Y/N): ");
                        String reply = sc.nextLine();

                        if (reply.trim().toUpperCase().equals("Y")) {
                            isExit = true;
                            isSaved = true;

                            ds_san.saveToFile("Facility.txt");
                            ds_san.saveUnitpriceToFile("UnitPrice.txt");
                            ds_booking.saveToFile("Booking.txt");

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
