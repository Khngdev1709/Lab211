package Manager;

import Inputter.Validation;
import Model.Unitprice;
import Model.Facility;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
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
public class FacilityList {
    private ArrayList<Facility> list;
    
    public FacilityList() {
        list = new ArrayList<>();
    }

    public void LoadFacilityFromFile(String filename) {
        FileReader f = null;
        BufferedReader br = null;
        int success = 0;
        int failed = 0;

        //Read Data
        try {
            f = new FileReader(filename);
            br = new BufferedReader(f);
            String line = br.readLine();
            while (br.ready()) {
                line = br.readLine();
                String[] results = line.split(",");

                //Parse Data
                if (results.length == 5) {
                    try {
                        String id = results[0].trim();
                        String name = results[1].trim();
                        String type = results[2].trim();
                        String loc = results[3].trim();
                        int capacity = Integer.parseInt(results[4].trim());

                        Facility fac = new Facility();
                        fac.setId(id);
                        fac.setName(name);
                        fac.setType(type);
                        fac.setLoc(loc);
                        fac.setCapacity(capacity);
                        //Added
                        list.add(fac);
                        success++;
                    } catch (Exception e) {
                        failed++;
                    }
                } else {
                    failed++;
                }
            }
            System.out.printf("%d rooms succesfully loaded.\n%d entries failed.\n", success, failed);
        } catch (Exception e) {
            System.out.println("File not found");
            failed++;
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.out.println("Error while reading file");
            }
        }
    }

    //This function is use to open file, read data and save to framtime-price
    public void loadUnitpriceFromFile(String filename) {
        FileReader f = null;
        BufferedReader br = null;
        try {
            f = new FileReader(filename);
            br = new BufferedReader(f);
            String line = br.readLine();
            while (br.ready()) {
                line = br.readLine().trim();
                if (line.length() > 0) {
                    String results[] = line.split(",");

                    //Parse Data
                    String facid = results[0].trim();
                    String from = results[1].trim();
                    String to = results[2].trim();
                    double price = Double.parseDouble(results[3].trim());

                    //Add Data
                    Unitprice u = new Unitprice(facid, from, to, price);
                    Facility find = findFacilityById(facid);
                    if (find != null) {
                        find.getFrametime_price().add(u);  //Added
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("File not found");
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.out.println("Error while reading file");
            }
        }
    }

    public Facility findFacilityByName(String kw) {
        String k = kw.trim();
        for (Facility f : list) {
            if (f.getName() != null && f.getName().trim().equalsIgnoreCase(k)) {
                return f;
            }
        }
        return null;
    }

    public Facility findFacilityById(String kw) {
        String k = kw.trim();
        for (Facility f : list) {
            if (f.getId() != null && f.getId().trim().equalsIgnoreCase(k)) {
                return f;
            }
        }
        return null;
    }

    public boolean updateFacility() {
        Scanner sc = new Scanner(System.in);
        Facility f = null;
        boolean cont = true;

        try {
            do {
                System.out.println("Choose facility ID or Name to update: ");
                System.out.println("1) Facility Name");
                System.out.println("2) ID");
                System.out.println("Choose 1 or 2 : ");
                String search = sc.nextLine().trim();
                //Search information by Name
                if ("1".equals(search)) {
                    String name = Inputter.Inputter.inputStringAllowEmpty("Enter facility name : ", Validation.F_Name_FMT);
                    if (name == null || name.isEmpty()) {
                        System.out.println("Name cannot be empty");
                    }
                    f = findFacilityByName(name);

                    //Search information by Id
                } else if ("2".equals(search)) {
                    String id = Inputter.Inputter.inputStringAllowEmpty("Enter facility id : ", Validation.F_ID_FMT);
                    if (id == null || id.isEmpty()) {
                        System.out.println("Id cannot be empty");
                    }
                    f = findFacilityById(id);

                    //No information found
                } else {
                    System.out.println("Invalid Choice");
                }

                //If it does not exist
                if (f == null) {
                    System.out.println("No facility or service found in database");
                    return false;
                }

                //Update new location
                String newLoc = Inputter.Inputter.inputStringAllowEmpty("Enter new location (blank = keep current): ", Validation.F_LOCATION_FMT);
                //Update new capacity
                Integer newCap = Inputter.Inputter.inputIntegerAllowEmpty("Enter new capacity (blank = keep current): ");
                if (!newLoc.isEmpty()) {
                    f.setLoc(newLoc);
                }
                if (newCap != null) {
                    if (newCap > 0) {
                        f.setCapacity(newCap);
                    }
                }

                //Input slot
                boolean found = false;
                LocalTime Start = Inputter.Inputter.inputTime("Enter slot start(H:MM): ");
                LocalTime End = Inputter.Inputter.inputTime("Enter slot end(H:MM): ");
//                    //Update new Price
//                    double price = Inputter.Inputter.inputIntegerAllowEmpty("Enter new price: ");

                ArrayList<Unitprice> list = f.getFrametime_price();
                for (Unitprice u : list) {
                    LocalTime d1 = LocalTime.parse(u.getFrom(), Validation.TIME_FMT);
                    LocalTime d2 = LocalTime.parse(u.getTo(), Validation.TIME_FMT);
                    if (Start.equals(d1) && End.equals(d2)) {
                        double newPrice = Inputter.Inputter.inputIntegerAllowEmpty("Enter your new price (blank = keep current): ");
                        if (newPrice > 0) {
                            u.setPrice(newPrice);
                            System.out.println("Updated price for slot from " + u.getFrom() + " to " + u.getTo());
                        }
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    System.out.println("No slot matched.");
                }

                System.out.println("Do you want to update another information (Y/N): ");
                String ans = sc.nextLine().trim();
                if (ans.equalsIgnoreCase("N")) {
                    System.out.println("Facility updated successfully !");
                    cont = false;
                }
            } while (cont);

        } catch (Exception e) {
            System.out.println("Update Failed");
        }
        return false;
    }

    public void displayFacility() {
        if (list.isEmpty()) {
            System.out.println("No facility have filled yet.");
        } else {
            System.out.printf("| %-8s | %-20s | %-13s | %-21s | %-10s |\n", "Id", "Facility Name", "Facility Type", "Location", "Capacity");
            System.out.println("-----------------------------------------------------------------------------------------");
            for (Facility f : list) {
                System.out.println(f);
            }
        }
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

    public void saveUnitpriceToFile(String filename) {
        FileOutputStream f = null;
        ObjectOutputStream of = null;
        try {
            f = new FileOutputStream(filename);
            of = new ObjectOutputStream(f);
            of.writeObject(list);
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
