/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Inputter.Validation;
import Model.Brand;
import Model.Car;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class CarList {

    private ArrayList<Car> list;
    private BrandList brandList;
    Scanner sc = new Scanner(System.in);

    public CarList(BrandList b) {
        list = new ArrayList<>();
        this.brandList = b;
    }

    public void readDataFromFile(String filename) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            while (br.ready()) {
                String line = br.readLine();
                String[] parts = line.split(",");
                if (line == null || line.isEmpty()) {
                    continue;
                }
                if (parts.length == 5) {
                    String carID = parts[0].trim();
                    String brandIDStr = parts[1].trim();
                    String carColor = parts[2].trim();
                    String carFrameID = parts[3].trim();
                    String carEngineID = parts[4].trim();

                    Brand brandIDObj = brandList.getBrandbyID(brandIDStr);

                    if (brandIDObj != null) {
                        Car c = new Car(carID, brandIDObj, carColor, carFrameID, carEngineID);
                        list.add(c);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error while opening file");
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.out.println("Error while reading file");
            }
        }
    }

    public void listCarsByBrandName() {
        Comparator<Car> carComparator = new Comparator<Car>() {
            @Override
            //sap xep theo ten thuong hieu (tang dan), neu giam dan thi dung reverse
            public int compare(Car c1, Car c2) {
                int brandCompare = c1.getBrandID().getBrandName().compareTo(c2.getBrandID().getBrandName());

                if (brandCompare != 0) {
                    return brandCompare;
                } else {
                    if (c1.getBrandID().getBrandPrice() < c1.getBrandID().getBrandPrice()) {
                        return 1;
                    } else if (c1.getBrandID().getBrandPrice() > c1.getBrandID().getBrandPrice()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        };
        System.out.println("\n---------------------- LIST AFTER SORTING ---------------------");
        Collections.sort(list, carComparator);
        System.out.printf("| %-7s | %-10s | %-10s | %-10s | %-10s |\n", "Car ID", "Brand ID", "Color", "Frame ID", "Engine ID");
        System.out.println("---------------------------------------------------------------");
        for (Car car : list) {
            System.out.println(car);
        }
//        System.out.println("\n--------------- CHECKING SORT LOGIC -----------------");
//        System.out.printf("| %-10s | %-30s | %-5s\n", "CarID", "Brand Name", "Price");
//        System.out.println("-------------------------------------------------------");
//        for (Car c : list) {
//            System.out.printf("| %-10s | %-30s | %.3fB|\n",
//                    c.getCarID(),
//                    c.getBrandID().getBrandName(), // Print name to check
//                    c.getBrandID().getBrandPrice() // Print price to check
//            );
//        }
    }

    public void displayCar() {
        if (list.isEmpty() || list == null) {
            System.out.println("Emty");
            return;
        }
        System.out.printf("| %-7s | %-10s | %-10s | %-10s | %-10s |\n", "Car ID", "Brand ID", "Color", "Frame ID", "Engine ID");
        System.out.println("---------------------------------------------------------------");
        for (Car car : list) {
            System.out.println(car.toString());
        }
    }

    public void searchCarsByBrandName(String code) {
        String kw = code.trim().toLowerCase();
        boolean found = false;

        System.out.println("------------ SEARCH RESULT -------------");
        for (Car c : list) {
            if (c.getBrandID().getBrandName().toLowerCase().contains(kw)) {
                System.out.println(c.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No car found!");
        }
    }

    public boolean findCarIDBoolean(String kw) {
        for (Car car : list) {
            if (car.getCarID().equals(kw)) {
                return true;
            }
        }
        return false;
    }

    public Car findCarIDStr(String kw) {
        for (Car car : list) {
            if (car.getCarID().equalsIgnoreCase(kw.trim())) {
                return car;
            }
        }
        return null;
    }

    public boolean addCar() {
        Car c = new Car();
        boolean addedAny = false;
        boolean cont = true;

        do {
            try {
                boolean found = false;
                //Input CarID
                do {
                    String carID = Inputter.Inputter.inputString("Enter car ID: ", Validation.CAR_ID_FMT);
                    if (findCarIDBoolean(carID)) {
                        System.out.println("Car is already exist! Please enter a new one: ");
                        found = false;
                    } else {
                        c.setCarID(carID);
                        found = true;
                    }
                } while (!found);

                //Input BrandID
                do {
                    String brandIDStr = Inputter.Inputter.inputString("Enter brand ID: ", Validation.BRAND_ID_FMT);
                    if (brandList.findBrand(brandIDStr)) {
                        Brand brandIDObj = brandList.getBrandbyID(brandIDStr);
                        c.setBrandID(brandIDObj);
                        found = true;

                    } else {
                        System.out.println("This brand does not exist!");
                        found = false;
                    }
                } while (!found);

                //Input CarColor
                String carColor = Inputter.Inputter.inputString("Enter car color: ", Validation.CAR_COLOR_FMT);
                c.setCarColor(carColor);
                String carFrameID = Inputter.Inputter.inputString("Enter car frame ID: ", Validation.CAR_FRAMEID_FMT);
                c.setCarFrameID(carFrameID);
                String carEngineID = Inputter.Inputter.inputString("Enter car engine ID: ", Validation.CAR_ENGINEID_FMT);
                c.setCarEngineID(carEngineID);

                list.add(c);
                addedAny = true;
                System.out.println("Added successfully!");
            } catch (Exception e) {
                System.out.println("Invalid format! Please enter it again: ");
            }
            System.out.println("Do you want to add another car ? (y/n): ");
            String choice = sc.nextLine().trim();
            if (choice.equalsIgnoreCase("n")) {
                cont = false;
            }
        } while (cont);
        return addedAny;
    }

    public boolean removeCarByID() {
        try {
            String removeID = Inputter.Inputter.inputString("Enter car ID you want to remove: ", Validation.CAR_ID_FMT);
            Car carID = findCarIDStr(removeID);
            if (carID != null) {
                System.out.printf("| %-7s | %-10s | %-10s | %-10s | %-10s |\n", "Car ID", "Brand ID", "Color", "Frame ID", "Engine ID");
                System.out.println("---------------------------------------------------------------");
                System.out.println(carID.toString());
                System.out.println("Do you want to delete this car(True/False): ");
                boolean reply = sc.nextBoolean();
                if (reply) {
                    list.remove(carID);
                    System.out.println("This car has been deleted!");
                    return true;
                } else {
                    System.out.println("Remove cancelled");
                    return false;
                }
            } else {
                System.out.println("This car does not exist!");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return false;
    }

    public void updateCarByID(String kw) {
        if (kw == null || kw.isEmpty()) {
            System.out.println("This car does not exist!");
            return;
        }
        Car c = findCarIDStr(kw);
        if (c != null) {
            try {
                System.out.println("------------------------ Current Info -------------------------");
                System.out.printf("| %-7s | %-10s | %-10s | %-10s | %-10s |\n", "Car ID", "Brand ID", "Color", "Frame ID", "Engine ID");
                System.out.println("---------------------------------------------------------------");
                System.out.println(c.toString());
                String newCarColor = Inputter.Inputter.inputStringAllowEmpty("Enter new car color (blank = keep current): ", Validation.CAR_COLOR_FMT);
                String newCarFrameID = Inputter.Inputter.inputStringAllowEmpty("Enter new car frame ID (blank = keep current): ", Validation.CAR_FRAMEID_FMT);
                String newCarEngineID = Inputter.Inputter.inputStringAllowEmpty("Enter new car enigne ID (blank = keep current): ", Validation.CAR_ENGINEID_FMT);

                if (!newCarColor.isEmpty()) {
                    c.setCarColor(newCarColor);
                }

                if (!newCarFrameID.isEmpty()) {
                    c.setCarFrameID(newCarFrameID);
                }

                if (!newCarEngineID.isEmpty()) {
                    c.setCarEngineID(newCarEngineID);
                }
                System.out.println("New car's information updated succesfully!");
            } catch (Exception e) {
                System.out.println("Incorrect format! Please type it again");
            }
        } else {
            System.out.println("This car does not exist!");
        }
    }

    public void listCarByColor(String kw) {
        boolean found = false;
        if (kw == null || kw.isEmpty()) {
            System.out.println("This car's color does not exist!");
            return;
        }
        System.out.printf("| %-7s | %-10s | %-10s | %-10s | %-10s |\n", "Car ID", "Brand ID", "Color", "Frame ID", "Engine ID");
        System.out.println("---------------------------------------------------------------");
        for (Car car : list) {
            if (car.getCarColor().trim().equalsIgnoreCase(kw.trim())) {
                System.out.println(car.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("This car's color does not exist!");
        }
    }

    public void saveToFile(String filename) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(filename);
            pw = new PrintWriter(fw);

            for (Car c : list) {
                // Format: CarID, BrandID, Color, FrameID, EngineID
                pw.println(c.getCarID() + ", "
                        + c.getBrandID().getBrandID() + ", "
                        + c.getCarColor() + ", "
                        + c.getCarFrameID() + ", "
                        + c.getCarEngineID());
            }

            System.out.println("Car list has been successfully saved to file : " + filename);
        } catch (Exception e) {
            System.out.println("Error saving cars: " + e.getMessage());
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                System.out.println("Error while closing file");
            }
        }
    }

    public void displayBrandDontHaveCar() {
        Comparator<Car> carComparator = new Comparator<Car>() {
            @Override
            //sap xep theo ten thuong hieu (tang dan), neu giam dan thi dung reverse
            public int compare(Car c1, Car c2) {
                int brandCompare = c1.getBrandID().getBrandName().compareTo(c2.getBrandID().getBrandName());

                if (brandCompare != 0) {
                    return brandCompare;
                } else {
                    if (c1.getBrandID().getBrandPrice() < c1.getBrandID().getBrandPrice()) {
                        return 1;
                    } else if (c1.getBrandID().getBrandPrice() > c1.getBrandID().getBrandPrice()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        };
        System.out.println("\n---------------------------- LIST AFTER SORTING ---------------------------");
        Collections.sort(list, carComparator);
        System.out.printf("| %-10s | %-30s | %-15s | %-8s |\n", "Brand ID", "Brand Name", "Sound", "Price");
        System.out.println("----------------------------------------------------------------------------");
        for (Car car : list) {
            if (!car.getBrandID().equals(car.getBrandID().getBrandID())) {
                System.out.println(car.getBrandID().toString());
            }
        }
    }
}
