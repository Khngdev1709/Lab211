/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.DecimalFormat;

/**
 *
 * @author DELL
 */
public class Brand {

    private String brandID;
    private String brandName;
    private String brandSound;
    private double brandPrice;
    DecimalFormat df = new DecimalFormat("0.###B");

    public Brand(String brandID, String brandName, String brandSound, double brandPrice) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandSound = brandSound;
        this.brandPrice = brandPrice;
    }

    public Brand() {
        this.brandID = "";
        this.brandName = "";
        this.brandSound = "";
        this.brandPrice = 0;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandSound() {
        return brandSound;
    }

    public void setBrandSound(String brandSound) {
        this.brandSound = brandSound;
    }

    public double getBrandPrice() {
        return brandPrice;
    }

    public void setBrandPrice(double brandPrice) {
        this.brandPrice = brandPrice;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-30s | %-15s | %-8s |", brandID, brandName, brandSound, df.format(brandPrice));
    }
}
