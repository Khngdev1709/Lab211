package Manager;

import Inputter.Validation;
import Model.Booking;
import Model.Unitprice;
import Model.Facility;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Khang
 */
public class BookingList {

    private ArrayList<Booking> list;
    DecimalFormat df = new DecimalFormat("#,##0 VND");

    public BookingList() {
        list = new ArrayList<>();
    }
    Scanner sc = new Scanner(System.in);

    //ham nay de tao 1 booking
    public boolean createBooking(FacilityList ds_san) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer name: ");
        String custName = sc.nextLine();
        String facid = Inputter.Inputter.inputString("Enter facility ID: ", Validation.F_ID_FMT);
        //Check if facility is exist
        Facility fac = ds_san.findFacilityById(facid);
        if (fac == null) {
            System.out.println("Facility ID not found!");
            return false;
        }
        String bookingID = Inputter.Inputter.inputString("Enter bookingID", Validation.BOOKING_ID);
        LocalDate bookingDate = Inputter.Inputter.inputDate("Enter booking date (YYYY-MM-DD): ");
        LocalTime from = Inputter.Inputter.inputTime("Enter booking time from (H:MM): ");
        LocalTime to = Inputter.Inputter.inputTime("Enter booking time to (H:MM): ");

        //Check if time is available
        if (!to.isAfter(from)) {
            System.out.println("End time must be after start time.");
            return false;
        }

        //Calculate money for booking
        double price = 0;
        ArrayList<Unitprice> khunggia = fac.getFrametime_price();
        for (Unitprice u : khunggia) {
            String begin = u.getFrom();
            String end = u.getTo();
            LocalTime avaiStart = LocalTime.parse(begin, Validation.TIME_FMT);
            LocalTime avaiEnd = LocalTime.parse(end, Validation.TIME_FMT);
            if ((!avaiStart.isAfter(from)) && (!avaiEnd.isBefore(to))) {
                price = u.getPrice();
                break;
            }
        }

        if (price == 0) {
            System.out.println("No available slot matches your booking time!");
            return false;
        }

        Booking b = new Booking();
        b.setBookingid(bookingID);
        b.setCreateddate(bookingDate);
        b.setCustName(custName);
        b.setTo(to);
        b.setFacid(facid);
        b.setFrom(from);
        b.setStatus(1);
        b.setPrice(price);
        list.add(b);
        //Booked
        System.out.println("Booking created successfully!");
        return true;
    }

    public void viewTodayBooking() {
        ArrayList<Booking> result = new ArrayList<>();
        LocalDate todayBooking = Inputter.Inputter.inputDateAllowEmpty("Enter booking date (leave blank for today: )");
        for (Booking b : list) {
            if (b.getCreateddate().equals(todayBooking)) {
                result.add(b);
            }
        }

        if (result.isEmpty()) {
            System.out.println("There are currently no courts or services booked !.");
            return;
        }
        //Sort Time
        result.sort((b1, b2) -> b1.getFrom().compareTo(b2.getFrom()));
        //Display
        System.out.println("========== Booking on " + todayBooking.format(Validation.DATE_FMT) + " ==========");
        System.out.printf("%-12s | %-6s | %-6s | %-8s | %-20s | %-6s%n",
                "FacilityID", "From", "To", "Price", "Customer", "Status");
        System.out.println("--------------------------------------------------------------------------------");
        for (Booking book : result) {
            String fromStr = book.getFrom().format(Validation.TIME_FMT);
            String toStr = book.getTo().format(Validation.TIME_FMT);
            System.out.printf("%-12s | %-6s | %-6s | %-8s | %-20s | %-6d%n",
                    book.getFacid(), fromStr, toStr, df.format(book.getPrice()), book.getCustName(), book.getStatus());
        }
    }

    public void removeBooking() {
        String b_ID = Inputter.Inputter.inputString("Enter booking ID to cancel: ", Validation.BOOKING_ID);
        Booking foundBooking = null;

        for (Booking b : list) {
            if (b.getBookingid().equalsIgnoreCase(b_ID)) {
                foundBooking = b;
                break;
            }
        }

        if (foundBooking == null) {
            System.out.println("Booking for ID " + b_ID + " could not be found");
            return;
        }

        LocalDateTime checkTime = LocalDateTime.of(foundBooking.getCreateddate(), foundBooking.getFrom());

        if (checkTime.isBefore(LocalDateTime.now())) {
            System.out.println("This booking ID " + b_ID + " cannot be canceled");
            return;
        }

        System.out.println("Booking information: ");
        System.out.printf("%-12s | %-6s | %-6s | %-8s | %-20s | %-6s%n",
                "FacilityID", "From", "To", "Price", "Customer", "Status");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-12s | %-6s | %-6s | %-8s | %-20s | %-6s%n", foundBooking.getFacid(), foundBooking.getFrom(),
                foundBooking.getTo(), foundBooking.getPrice(), foundBooking.getCustName(), foundBooking.getStatus());
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Do you really want to cancel this court booking(Y/N)? ");
        String confirm = sc.nextLine().trim();

        if (confirm.equalsIgnoreCase("Y")) {
            list.remove(foundBooking);
            System.out.println("The booking ID: " + b_ID + " has been removed successfully!");
        } else {
            System.out.println("Cancel removing this booking ID: " + b_ID);
        }
    }

    public boolean findBookingID(String code) {
        for (Booking booking : list) {
            if (booking.getBookingid().trim().equalsIgnoreCase(code.trim())) {
                return true;
            }
        }
        return false;
    }

    public void saveToFile(String filename) {
        FileOutputStream f = null;
        ObjectOutputStream of = null;
        try {
            f = new FileOutputStream(filename);
            of = new ObjectOutputStream(f);
            of.writeObject(of);
            of.flush();

            System.out.println("Facility list hase been successfully saved to file : " + filename);
        } catch (Exception e) {
            System.out.println("Error while saving file");
        } finally {
            try {
                if (of != null) {
                    of.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing file writer.");
            }
        }
    }
}
