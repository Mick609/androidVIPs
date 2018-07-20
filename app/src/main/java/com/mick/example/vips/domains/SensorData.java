package com.mick.example.vips.domains;

public class SensorData {

    public String sensorName;
    public long timeStamp;
    public float x;
    public float y;
    public float z;

    public SensorData(String sensorName, float x, float y, float z) {
        this.sensorName = sensorName;
        this.x = x;
        this.y = y;
        this.z = z;
        timeStamp = System.currentTimeMillis();
    }

    public float getAxisEuclideanDestance() {
        return x * x + y * y + z * z;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
