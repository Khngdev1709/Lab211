package Model;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Khang
 */
public class Booking {

    private String bookingid;
    private String facid;
    private LocalDate createddate;
    private LocalTime from;
    private LocalTime to;
    private double price;
    private String custName;
    private int status;

    public Booking(String bookingid, String facid, LocalDate createddate, LocalTime from, LocalTime to, double price, String custName, int status) {
        this.bookingid = bookingid;
        this.facid = facid;
        this.createddate = createddate;
        this.from = from;
        this.to = to;
        this.price = price;
        this.custName = custName;
        this.status = status;
    }

    public Booking() {
    }

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public String getFacid() {
        return facid;
    }

    public void setFacid(String facid) {
        this.facid = facid;
    }

    public LocalDate getCreateddate() {
        return createddate;
    }

    public void setCreateddate(LocalDate createddate) {
        this.createddate = createddate;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingid=" + bookingid + ", facid=" + facid + ", createddate=" + createddate + ", from=" + from + ", to=" + to + ", price=" + price + ", custName=" + custName + ", status=" + status + '}';
    }
    

}
