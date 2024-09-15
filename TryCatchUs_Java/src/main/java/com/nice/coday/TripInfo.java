package com.nice.coday;

public class TripInfo {
    private int id;
    private String vehicleType;
    private double remainingBatteryPercentage;
    private String entryPoint;
    private String exitPoint;

    public TripInfo(int id, String vehicleType, double remainingBatteryPercentage, String entryPoint, String exitPoint) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.remainingBatteryPercentage = remainingBatteryPercentage;
        this.entryPoint = entryPoint;
        this.exitPoint = exitPoint;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public double getRemainingBatteryPercentage() {
        return remainingBatteryPercentage;
    }

    public String getEntryPoint() {
        return entryPoint;
    }

    public String getExitPoint() {
        return exitPoint;
    }

    // Setters (if needed)
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setVehicleType(String vehicleType) {
//        this.vehicleType = vehicleType;
//    }
//
//    public void setRemainingBatteryPercentage(double remainingBatteryPercentage) {
//        this.remainingBatteryPercentage = remainingBatteryPercentage;
//    }
//
//    public void setEntryPoint(String entryPoint) {
//        this.entryPoint = entryPoint;
//    }
//
//    public void setExitPoint(String exitPoint) {
//        this.exitPoint = exitPoint;
//    }
}


