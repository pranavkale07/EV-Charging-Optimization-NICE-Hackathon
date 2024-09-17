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

}

