package com.nice.coday;

public class VehicleInfo {
    private final String vehicleType;
    private final int numberOfUnitsForFullyCharge;
    private final int mileage;

    public VehicleInfo(String vehicleType, int units, int mileage) {
        this.vehicleType = vehicleType;
        this.numberOfUnitsForFullyCharge = units;
        this.mileage = mileage;
    }

    // Getters
    public String getVehicleType() {
        return vehicleType;
    }

    public int getNumberOfUnitsForFullyCharge() {
        return numberOfUnitsForFullyCharge;
    }

    public int getMileage() {
        return mileage;
    }

    // Setters (if needed)
//    public void setVehicleType(String vehicleType) {
//        this.vehicleType = vehicleType;
//    }
//
//    public void setNumberOfUnitsForFullyCharge(int units) {
//        this.numberOfUnitsForFullyCharge = units;
//    }
//
//    public void setMileage(int mileage) {
//        this.mileage = mileage;
//    }

    @Override
    public String toString() {
        return "VehicleInfo{" +
                "vehicleType='" + vehicleType + '\'' +
                ", numberOfUnitsForFullyCharge=" + numberOfUnitsForFullyCharge +
                ", mileage=" + mileage +
                '}';
    }
}

