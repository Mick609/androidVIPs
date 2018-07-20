package com.mick.example.vips.domains;

import android.content.Context;

import com.mick.example.vips.models.model1;
import com.mick.example.vips.utilities.AppManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;

public class Gesture {
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

    public ArrayList<SensorData> accDataList;
    public ArrayList<SensorData> gyroDataList;
    public GestureFeature gestureFeature;
    AppManager mAppManager;
    Context mContext;
    model1 model = new model1();

    public Gesture(Context context) {
        mContext = context;
        accDataList = new ArrayList<>();
        gyroDataList = new ArrayList<>();
        mAppManager = new AppManager(mContext);
    }

    public ArrayList<SensorData> getaccDataList() {
        return accDataList;
    }

    public ArrayList<SensorData> getgyroDataList() {
        return gyroDataList;
    }

    public void setaccDataList(ArrayList<SensorData> sensorDataList) {
        this.accDataList = sensorDataList;
    }

    public void setgyroDataList(ArrayList<SensorData> sensorDataList) {
        this.gyroDataList = sensorDataList;
    }

    public GestureFeature getGestureFeature() {
        return gestureFeature;
    }

    public void setGestureFeature(GestureFeature gestureFeature) {
        this.gestureFeature = gestureFeature;
    }

    public GestureFeature calculateFeatures() {
        for (int i = 0; i < accDataList.size(); i++) {
            SensorData dataInstance = accDataList.get(i);
            if (i == 0) {
                accXmax = dataInstance.getX();
                accYmax = dataInstance.getY();
                accZmax = dataInstance.getZ();

                accXmin = dataInstance.getX();
                accYmin = dataInstance.getY();
                accZmin = dataInstance.getZ();

                accXsum = dataInstance.getX();
                accYsum = dataInstance.getY();
                accZsum = dataInstance.getZ();
            } else {
                if (dataInstance.getX() > accXmax) {
                    accXmax = dataInstance.getX();
                }
                if (dataInstance.getY() > accYmax) {
                    accYmax = dataInstance.getY();
                }
                if (dataInstance.getZ() > accZmax) {
                    accZmax = dataInstance.getZ();
                }


                if (dataInstance.getX() < accXmin) {
                    accXmin = dataInstance.getX();
                }
                if (dataInstance.getY() < accYmin) {
                    accYmin = dataInstance.getY();
                }
                if (dataInstance.getZ() < accZmin) {
                    accZmin = dataInstance.getZ();
                }

                accXsum += dataInstance.getX();
                accYsum += dataInstance.getY();
                accZsum += dataInstance.getZ();
            }
        }

        double accXmean = accXsum / accDataList.size();
        double accYmean = accYsum / accDataList.size();
        double accZmean = accZsum / accDataList.size();


        double Xvariance = 0;
        double Yvariance = 0;
        double Zvariance = 0;
        for (int i = 0; i < accDataList.size(); i++) {
            SensorData dataInstance = accDataList.get(i);
            Xvariance += (dataInstance.getX() - accXmean) * (dataInstance.getX() - accXmean);
            Yvariance += (dataInstance.getY() - accYmean) * (dataInstance.getY() - accYmean);
            Zvariance += (dataInstance.getZ() - accZmean) * (dataInstance.getZ() - accZmean);
        }

        Xvariance = Xvariance / (accDataList.size() - 1);
        Yvariance = Yvariance / (accDataList.size() - 1);
        Zvariance = Zvariance / (accDataList.size() - 1);

        accXSD = Math.sqrt(Xvariance);
        accYSD = Math.sqrt(Yvariance);
        accZSD = Math.sqrt(Zvariance);


//        end of acc, start of gyro

        for (int i = 0; i < gyroDataList.size(); i++) {
            SensorData dataInstance = gyroDataList.get(i);
            if (i == 0) {
                gyroXmax = dataInstance.getX();
                gyroYmax = dataInstance.getY();
                gyroZmax = dataInstance.getZ();

                gyroXmin = dataInstance.getX();
                gyroYmin = dataInstance.getY();
                gyroZmin = dataInstance.getZ();

                gyroXsum = dataInstance.getX();
                gyroYsum = dataInstance.getY();
                gyroZsum = dataInstance.getZ();
            } else {
                if (dataInstance.getX() > gyroXmax) {
                    gyroXmax = dataInstance.getX();
                }
                if (dataInstance.getY() > gyroYmax) {
                    gyroYmax = dataInstance.getY();
                }
                if (dataInstance.getZ() > gyroZmax) {
                    gyroZmax = dataInstance.getZ();
                }


                if (dataInstance.getX() < gyroXmin) {
                    gyroXmin = dataInstance.getX();
                }
                if (dataInstance.getY() < gyroYmin) {
                    gyroYmin = dataInstance.getY();
                }
                if (dataInstance.getZ() < gyroZmin) {
                    gyroZmin = dataInstance.getZ();
                }

                gyroXsum += dataInstance.getX();
                gyroYsum += dataInstance.getY();
                gyroZsum += dataInstance.getZ();
            }
        }
        double gyroXmean = gyroXsum / gyroDataList.size();
        double gyroYmean = gyroYsum / gyroDataList.size();
        double gyroZmean = gyroZsum / gyroDataList.size();


        Xvariance = 0;
        Yvariance = 0;
        Zvariance = 0;
        for (int i = 0; i < gyroDataList.size(); i++) {
            SensorData dataInstance = gyroDataList.get(i);
            Xvariance += (dataInstance.getX() - gyroXmean) * (dataInstance.getX() - gyroXmean);
            Yvariance += (dataInstance.getY() - gyroYmean) * (dataInstance.getY() - gyroYmean);
            Zvariance += (dataInstance.getZ() - gyroZmean) * (dataInstance.getZ() - gyroZmean);
        }

        Xvariance = Xvariance / (gyroDataList.size() - 1);
        Yvariance = Yvariance / (gyroDataList.size() - 1);
        Zvariance = Zvariance / (gyroDataList.size() - 1);

        gyroXSD = Math.sqrt(Xvariance);
        gyroYSD = Math.sqrt(Yvariance);
        gyroZSD = Math.sqrt(Zvariance);

        gestureFeature = new GestureFeature(
                accXmax, accYmax, accZmax,
                gyroXmax, gyroYmax, gyroZmax,
                accXmin, accYmin, accZmin,
                gyroXmin, gyroYmin, gyroZmin,
                accXsum, accYsum, accZsum,
                gyroXsum, gyroYsum, gyroZsum,
                accXSD, accYSD, accZSD,
                gyroXSD, gyroYSD, gyroZSD);
//        mAppManager.saveFileIntoDevice(gestureFeature.toString(), "data_tone");

        return gestureFeature;
    }

    public String modelClassification() {
        double[] ret = model.expression(
                accXmax, accYmax, accZmax,
                gyroXmax, gyroYmax, gyroZmax,
                accXmin, accYmin, accZmin,
                gyroXmin, gyroYmin, gyroZmin,
                accXsum, accYsum, accZsum,
                gyroXsum, gyroYsum, gyroZsum,
                accXSD, accYSD, accZSD,
                gyroXSD, gyroYSD, gyroZSD);

        DoubleSummaryStatistics result = Arrays.stream(ret).summaryStatistics();

        double max = result.getMax();

        if (max == ret[1]) {
            return ("SLAP");
        } else if (max == ret[2]) {
            return ("TONE");
        } else if (max == ret[3]) {
            return ("BASS");
        } else return "idle";
    }
}
