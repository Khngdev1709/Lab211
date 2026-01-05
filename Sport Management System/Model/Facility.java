package Model;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Khang
 */
public class Facility {

    private String id;
    private String name;
    private String type;
    private String loc;
    private int capacity;
    private ArrayList<Unitprice> frametime_price;

    public Facility() {
        id = "";
        name = "";
        type = "";
        loc = "";
        capacity = 0;
        frametime_price = new ArrayList<>();
    }

    public Facility(String id, String name, String type, String loc, int capacity, ArrayList<Unitprice> frametime_price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.loc = loc;
        this.capacity = capacity;
        this.frametime_price = frametime_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Unitprice> getFrametime_price() {
        return frametime_price;
    }

    public void setFrametime_price(ArrayList<Unitprice> frametime_price) {
        this.frametime_price = frametime_price;
    }

    @Override
    public String toString() {
        String s1 = String.format("| %-8s | %-20s | %-13s | %-21s | %-10d |", id, name, type, loc, capacity);
        String s2 = "";
        for (Unitprice unitprice : frametime_price) {
            s2 = s2 + unitprice.toString() + "\n";

        }
        return s1 + "\n" + s2;

    }

}
