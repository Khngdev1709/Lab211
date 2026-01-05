/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DELL
 */
public class Car {

    private String carID;
    private Brand brandID;
    private String carColor;
    private String carFrameID;
    private String carEngineID;

    public Car(String carID, Brand brandID, String carColor, String carFrameID, String carEngineID) {
        this.carID = carID;
        this.brandID = brandID;
        this.carColor = carColor;
        this.carFrameID = carFrameID;
        this.carEngineID = carEngineID;
    }

    public Car() {

    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrandID() {
        return brandID;
    }

    public void setBrandID(Brand brandID) {
        this.brandID = brandID;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarFrameID() {
        return carFrameID;
    }

    public void setCarFrameID(String carFrameID) {
        this.carFrameID = carFrameID;
    }

    public String getCarEngineID() {
        return carEngineID;
    }

    public void setCarEngineID(String carEngineID) {
        this.carEngineID = carEngineID;
    }

    @Override
    public String toString() {
        return String.format("| %-7s | %-10s | %-10s | %-10s | %-10s |", carID, brandID.getBrandID(), carColor, carFrameID, carEngineID);
    }
}
