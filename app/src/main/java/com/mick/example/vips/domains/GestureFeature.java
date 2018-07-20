package com.mick.example.vips.domains;

public class GestureFeature {
    private double accXmax;
    private double accYmax;
    private double accZmax;
    private double gyroXmax;
    private double gyroYmax;
    private double gyroZmax;

    private double accXmin;
    private double accYmin;
    private double accZmin;
    private double gyroXmin;
    private double gyroYmin;
    private double gyroZmin;

    //    Sum and mean shows the same feature of data
    private double accXsum;
    private double accYsum;
    private double accZsum;
    private double gyroXsum;
    private double gyroYsum;
    private double gyroZsum;

    //    Standard Deviation
    private double accXSD;
    private double accYSD;
    private double accZSD;
    private double gyroXSD;
    private double gyroYSD;
    private double gyroZSD;

    public GestureFeature(double accXmax, double accYmax, double accZmax, double gyroXmax, double gyroYmax, double gyroZmax, double accXmin, double accYmin, double accZmin, double gyroXmin, double gyroYmin, double gyroZmin, double accXsum, double accYsum, double accZsum, double gyroXsum, double gyroYsum, double gyroZsum, double accXSD, double accYSD, double accZSD, double gyroXSD, double gyroYSD, double gyroZSD) {
        accXmax = accXmax;
        accYmax = accYmax;
        accZmax = accZmax;
        gyroXmax = gyroXmax;
        gyroYmax = gyroYmax;
        gyroZmax = gyroZmax;
        accXmin = accXmin;
        accYmin = accYmin;
        accZmin = accZmin;
        gyroXmin = gyroXmin;
        gyroYmin = gyroYmin;
        gyroZmin = gyroZmin;
        accXsum = accXsum;
        accYsum = accYsum;
        accZsum = accZsum;
        gyroXsum = gyroXsum;
        gyroYsum = gyroYsum;
        gyroZsum = gyroZsum;
        accXSD = accXSD;
        accYSD = accYSD;
        accZSD = accZSD;
        gyroXSD = gyroXSD;
        gyroYSD = gyroYSD;
        gyroZSD = gyroZSD;
    }

    public String toString() {
        return accXmax + "," + accYmax + "," + accZmax + ","
                + gyroXmax + "," + gyroYmax + "," + gyroZmax + "," +
                accXmin + "," + accYmin + "," + accZmin + "," +
                gyroXmin + "," + gyroYmin + "," + gyroZmin + "," +
                accXsum + "," + accYsum + "," + accZsum + "," +
                gyroXsum + "," + gyroYsum + "," + gyroZsum + "," +
                accXSD + "," + accYSD + "," + accZSD + "," +
                gyroXSD + "," + gyroYSD + "," + gyroZSD;
    }

    public double getaccXmax() {
        return accXmax;
    }

    public void setaccXmax(double accXmax) {
        accXmax = accXmax;
    }

    public double getaccYmax() {
        return accYmax;
    }

    public void setaccYmax(double accYmax) {
        accYmax = accYmax;
    }

    public double getaccZmax() {
        return accZmax;
    }

    public void setaccZmax(double accZmax) {
        accZmax = accZmax;
    }

    public double getgyroXmax() {
        return gyroXmax;
    }

    public void setgyroXmax(double gyroXmax) {
        gyroXmax = gyroXmax;
    }

    public double getgyroYmax() {
        return gyroYmax;
    }

    public void setgyroYmax(double gyroYmax) {
        gyroYmax = gyroYmax;
    }

    public double getgyroZmax() {
        return gyroZmax;
    }

    public void setgyroZmax(double gyroZmax) {
        gyroZmax = gyroZmax;
    }

    public double getaccXmin() {
        return accXmin;
    }

    public void setaccXmin(double accXmin) {
        accXmin = accXmin;
    }

    public double getaccYmin() {
        return accYmin;
    }

    public void setaccYmin(double accYmin) {
        accYmin = accYmin;
    }

    public double getaccZmin() {
        return accZmin;
    }

    public void setaccZmin(double accZmin) {
        accZmin = accZmin;
    }

    public double getgyroXmin() {
        return gyroXmin;
    }

    public void setgyroXmin(double gyroXmin) {
        gyroXmin = gyroXmin;
    }

    public double getgyroYmin() {
        return gyroYmin;
    }

    public void setgyroYmin(double gyroYmin) {
        gyroYmin = gyroYmin;
    }

    public double getgyroZmin() {
        return gyroZmin;
    }

    public void setgyroZmin(double gyroZmin) {
        gyroZmin = gyroZmin;
    }

    public double getaccXsum() {
        return accXsum;
    }

    public void setaccXsum(double accXsum) {
        accXsum = accXsum;
    }

    public double getaccYsum() {
        return accYsum;
    }

    public void setaccYsum(double accYsum) {
        accYsum = accYsum;
    }

    public double getaccZsum() {
        return accZsum;
    }

    public void setaccZsum(double accZsum) {
        accZsum = accZsum;
    }

    public double getgyroXsum() {
        return gyroXsum;
    }

    public void setgyroXsum(double gyroXsum) {
        gyroXsum = gyroXsum;
    }

    public double getgyroYsum() {
        return gyroYsum;
    }

    public void setgyroYsum(double gyroYsum) {
        gyroYsum = gyroYsum;
    }

    public double getgyroZsum() {
        return gyroZsum;
    }

    public void setgyroZsum(double gyroZsum) {
        gyroZsum = gyroZsum;
    }

    public double getaccXSD() {
        return accXSD;
    }

    public void setaccXSD(double accXSD) {
        accXSD = accXSD;
    }

    public double getaccYSD() {
        return accYSD;
    }

    public void setaccYSD(double accYSD) {
        accYSD = accYSD;
    }

    public double getaccZSD() {
        return accZSD;
    }

    public void setaccZSD(double accZSD) {
        accZSD = accZSD;
    }

    public double getgyroXSD() {
        return gyroXSD;
    }

    public void setgyroXSD(double gyroXSD) {
        gyroXSD = gyroXSD;
    }

    public double getgyroYSD() {
        return gyroYSD;
    }

    public void setgyroYSD(double gyroYSD) {
        gyroYSD = gyroYSD;
    }

    public double getgyroZSD() {
        return gyroZSD;
    }

    public void setgyroZSD(double gyroZSD) {
        gyroZSD = gyroZSD;
    }
}
