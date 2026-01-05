/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inputter;

/**
 *
 * @author DELL
 */
public class Validation {

    //Brand Pattern
    public static final String BRAND_ID_FMT = "^B[A-Za-z0-9\\-]+$";
    public static final String BRAND_NAME_FMT = "^BMW[A-Za-z0-9\\s\\-\\(\\)\\.]*$";
    public static final String BRAND_SOUND_FMT = "^[A-Za-z\\s]+$";

    //Car Pattern
    public static final String CAR_ID_FMT = "^C\\d{2}$";
    public static final String CAR_COLOR_FMT = "^[A-Za-z]+$";
    public static final String CAR_FRAMEID_FMT = "^F\\d{5}$";
    public static final String CAR_ENGINEID_FMT = "^E\\d{5}$";
}
