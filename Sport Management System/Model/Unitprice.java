package Model;

import java.text.DecimalFormat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Khang
 */
public class Unitprice {

    private String id;
    private String from;
    private String to;
    private double price;
    DecimalFormat df = new DecimalFormat("#,##0 VND");

    public Unitprice(String id, String from, String to, double price) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.price = price;
    }

    public Unitprice() {
        this.id = "";
        this.from = "";
        this.to = "";
        this.price = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("| %-8s | from: %-14s | to: %-9s | price: %-14s |", id, from, to, df.format(price));
    }

}
