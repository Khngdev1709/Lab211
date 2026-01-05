/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inputter;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author ADMIN
 */
public class Validation {

    //Format Facility ID
    public static final String F_ID_FMT = "^[A-Z]{2,4}-\\d{1,3}$";
    //Format Facility Name
    public static final String F_Name_FMT = "^[A-Za-z0-9\\s]+$";
    //Format Facility Location
    public static final String F_LOCATION_FMT = "^[A-Za-z0-9\\s,\\-]+$";

    //Date Regex
    public static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    //Time Regex
    public static final String TIME_REGEX = "^(?:[01]?\\d|2[0-3]):[0-5]\\d$";

    //Format bookingID
    public static final String BOOKING_ID = "^B\\d{2}$";
    //Format DateTime
    public static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //Format Date
    public static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //Format Time
    public static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("H:mm");

}
