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
import java.util.Scanner;

/**
 *
 * @author DELL
 */

public class BrandList {

    private ArrayList<Brand> list; 
    private CarList carList;
    Scanner sc = new Scanner(System.in);

    public BrandList() {
        list = new ArrayList<>();
    }

    public BrandList(CarList c) {
        this.carList = c;
    }

    public void readDataFromFile(String filename) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            while (br.ready()) {
                String line = br.readLine();
                String[] parts = line.split("[:,]");
                if (line == null || line.isEmpty()) {
                    continue;
                }
                if (parts.length == 4) {
                    String brandID = parts[0].trim();
                    String brandName = parts[1].trim();
                    String brandSound = parts[2].trim();
                    String brandPrice = parts[3].trim();

                    //Cut off the letter "B" in Price
                    if (brandPrice.endsWith("B")) {
                        String brandPriceSplitted = brandPrice.substring(0, brandPrice.length() - 1);
                        if (findBrand(brandID)) {
                            continue;
                        } else {
                            Brand b = new Brand(brandID, brandName, brandSound, Double.parseDouble(brandPriceSplitted));
                            list.add(b);
                        }
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

    public void displayBrand() {
        if (list.isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.printf("| %-10s | %-30s | %-15s | %-8s |\n", "Brand ID", "Brand Name", "Sound", "Price");
            System.out.println("----------------------------------------------------------------------------");
            for (Brand b : list) {
                System.out.println(b);
            }
        }
    }

    public boolean findBrand(String code) {
        for (Brand b : list) {
            if (b.getBrandID().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public Brand getBrandbyID(String kw) {
        if (kw.isEmpty() || kw == null) {
            return null;
        }
        for (Brand b : list) {
            if (b.getBrandID().trim().equalsIgnoreCase(kw)) {
                return b;
            }
        }
        return null;
    }

    public boolean addBrand() {
        boolean addedAny = false;
        boolean cont = true;

        do {
            Brand bra = new Brand();
            try {
                boolean validID = false;
                do {
                    String brandID = Inputter.Inputter.inputString("Enter brand ID: ", Validation.BRAND_ID_FMT);
                    if (!findBrand(brandID)) {
                        bra.setBrandID(brandID);
                        validID = true;
                    } else {
                        System.out.println("ID already exist !!! Please enter ID again: ");
                        validID = false;
                    }
                } while (!validID);

                //Input Brand Name
                String brandName = Inputter.Inputter.inputString("Enter brand name: ", Validation.BRAND_NAME_FMT);
                bra.setBrandName(brandName);
                //Input Brand Sound
                String brandSound = Inputter.Inputter.inputString("Enter brand sound: ", Validation.BRAND_SOUND_FMT);
                bra.setBrandSound(brandSound);
                //Input Brand Price
                double brandPrice = Inputter.Inputter.inputDouble("Enter brand price: ");
                bra.setBrandPrice(brandPrice);
                //Added
                list.add(bra);
                addedAny = true;
                System.out.println("Added successfully!");
            } catch (Exception e) {
                System.out.println("Invalid format! Please enter it again: ");
            }
            System.out.println("Do you want to add another brand ? (y/n): ");
            String choice = sc.nextLine().trim();
            if (choice.equalsIgnoreCase("n")) {
                cont = false;
            }
        } while (cont);
        return addedAny;
    }

    public void searchBrandByID(String kw) {
        boolean found = false;
        if (kw == null || kw.isEmpty()) {
            System.out.println("This brand does not exist!");
            return;
        }
        for (Brand b : list) {
            if (b.getBrandID().trim().equalsIgnoreCase(kw.trim())) {
                System.out.printf("| %-10s | %-30s | %-15s | %-8s |\n", "Brand ID", "Brand Name", "Sound", "Price");
                System.out.println("----------------------------------------------------------------------------");
                System.out.println(b);
                found = true;
                break;
            }
        }

        if (found == false) {
            System.out.println("This brand does not exist!");
        }
    }

    public void updateBrandByID(String kw) {
        if (kw == null || kw.isEmpty()) {
            System.out.println("This brand does not exist!");
            return;
        }
        Brand bra = getBrandbyID(kw);
        if (bra != null) {
            try {
                System.out.println("----------------------------- Current brand info ---------------------------");
                System.out.printf("| %-10s | %-30s | %-15s | %-8s |\n", "Brand ID", "Brand Name", "Sound", "Price");
                System.out.println("----------------------------------------------------------------------------");
                System.out.println(bra.toString());
                //Input new information
                String newBrandName = Inputter.Inputter.inputStringAllowEmpty("Enter new brand name (blank = keep current): ", Validation.BRAND_NAME_FMT);
                String newBrandSound = Inputter.Inputter.inputStringAllowEmpty("Enter new brand sound (blank = keep current): ", Validation.BRAND_SOUND_FMT);
                double newBrandPrice = Inputter.Inputter.inputDoubleAllowEmpty("Enter new brand price (blank = keep current): ");

                //Set new information
                if (!newBrandName.isEmpty()) {
                    bra.setBrandName(newBrandName);
                }
                if (!newBrandSound.isEmpty()) {
                    bra.setBrandSound(newBrandSound);
                }
                if (newBrandPrice != -1) {
                    bra.setBrandPrice(newBrandPrice);
                }
                System.out.println("Brand updated successfully!");

            } catch (Exception e) {
                System.out.println("Invalid format! Please type it again");
            }
        } else {
            System.out.println("This brand does not exist!");
        }
    }

    public void listBrandByPrice(double price) {
        boolean found = false;
        System.out.printf("| %-10s | %-30s | %-15s | %-8s |\n", "Brand ID", "Brand Name", "Sound", "Price");
        System.out.println("----------------------------------------------------------------------------");
        for (Brand b : list) {
            if (b.getBrandPrice() <= price) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("There is no brand with a price lower than or equal to the price you just entered.");
        }
    }

    public void saveToFile(String filename) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(filename);
            pw = new PrintWriter(fw);

            for (Brand b : list) {
                // Format: ID, Name, SoundBrand: Price
                // Vd: B7-2018, BMW 730Li (2018), Harman Kardon: 3.749
                pw.println(b.getBrandID() + ", "
                        + b.getBrandName() + ", "
                        + b.getBrandSound() + ": "
                        + b.getBrandPrice());
            }
            System.out.println("Brand list has been successfully saved to file : " + filename);

        } catch (Exception e) {
            System.out.println("Error while saving file");
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing file writer.");
            }
        }
    }

    public void displayBrandDontHaveCar(ArrayList<Car> carList) {

        System.out.println("\n--- BRANDS WITHOUT CARS ---");
        System.out.printf("| %-10s | %-30s | %-15s | %-8s |\n", "Brand ID", "Brand Name", "Sound", "Price");
        System.out.println("----------------------------------------------------------------------------");

        boolean foundAny = false;
        for (Brand b : this.list) {
            boolean isUsed = false;

            for (Car c : carList) {
                if (c.getBrandID().getBrandID().equals(b.getBrandID())) {
                    isUsed = true; 
                    break;
                }
            }

            if (!isUsed) {
                System.out.println(b.toString());
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("All brands have cars!");
        }
    }
}
