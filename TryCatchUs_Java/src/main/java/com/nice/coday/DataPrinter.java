package com.nice.coday;

import java.util.HashMap;
import java.util.Map;

public class DataPrinter {

    public static void printAllDataStructures(HashMap<String, Integer> entryExitPointInfo,
                                              HashMap<String, Integer> chargingStationInfo,
                                              HashMap<String, VehicleInfo> vehicleTypeInfo,
                                              HashMap<String, HashMap<String, Long>> timeToChargeVehicleInfo,
                                              HashMap<Integer, TripInfo> tripDetailsInfo){
        printEntryExitPointInfo(entryExitPointInfo);
        printChargingStationInfo(chargingStationInfo);
        printVehicleTypeInfo(vehicleTypeInfo);
        printTimeToChargeVehicleInfo(timeToChargeVehicleInfo);
        printTripDetailsInfo(tripDetailsInfo);
    }

    // Print entryExitPointInfo
    public static void printEntryExitPointInfo(HashMap<String, Integer> entryExitPointInfo) {
        System.out.println("EntryExitPointInfo");
        System.out.println("{EntryExitPoint, DistanceFromStart}");
        for (Map.Entry<String, Integer> entry : entryExitPointInfo.entrySet()) {
            System.out.println("{" + entry.getKey() + ", " + entry.getValue() + "}");
        }
    }

    // Print chargingStationInfo
    public static void printChargingStationInfo(HashMap<String, Integer> chargingStationInfo) {
        System.out.println("ChargingStationInfo");
        System.out.println("{ChargingStation, DistanceFromStart}");
        for (Map.Entry<String, Integer> entry : chargingStationInfo.entrySet()) {
            System.out.println("{" + entry.getKey() + ", " + entry.getValue() + "}");
        }
    }

    // Print vehicleTypeInfo
    public static void printVehicleTypeInfo(HashMap<String, VehicleInfo> vehicleTypeInfo) {
        System.out.println("VehicleTypeInfo");
        System.out.println("{VehicleType, NumberOfUnitsForFullyCharge, Mileage}");
        for (Map.Entry<String, VehicleInfo> entry : vehicleTypeInfo.entrySet()) {
            VehicleInfo vehicle = entry.getValue();
            System.out.println("{" + entry.getKey() + ", " +
                    vehicle.getNumberOfUnitsForFullyCharge() + ", " +
                    vehicle.getMileage() + "}");
        }
    }

    // Print timeToChargeVehicleInfo
    public static void printTimeToChargeVehicleInfo(HashMap<String, HashMap<String, Long>> timeToChargeVehicleInfo) {
        System.out.println("TimeToChargeVehicleInfo");
        System.out.println("{VehicleType, ChargingStation, TimeToChargePerUnit}");
        for (Map.Entry<String, HashMap<String, Long>> entry : timeToChargeVehicleInfo.entrySet()) {
            String vehicleType = entry.getKey();
            HashMap<String, Long> chargingInfo = entry.getValue();
            for (Map.Entry<String, Long> innerEntry : chargingInfo.entrySet()) {
                System.out.println("{" + vehicleType + ", " +
                        innerEntry.getKey() + ", " +
                        innerEntry.getValue() + "}");
            }
        }
    }

    // Print tripDetailsInfo
    public static void printTripDetailsInfo(HashMap<Integer, TripInfo> tripDetailsInfo) {
        System.out.println("TripDetailsInfo");
        System.out.println("{Id, VehicleType, RemainingBatteryPercentage, EntryPoint, ExitPoint}");
        for (Map.Entry<Integer, TripInfo> entry : tripDetailsInfo.entrySet()) {
            TripInfo trip = entry.getValue();
            System.out.println("{" + trip.getId() + ", " +
                    trip.getVehicleType() + ", " +
                    trip.getRemainingBatteryPercentage() + ", " +
                    trip.getEntryPoint() + ", " +
                    trip.getExitPoint() + "}");
        }
    }

    public static void printConsumptionDetails(HashMap<String, ConsumptionDetails> consumptionDetails) {
        System.out.println("Consumption Details");
        for (ConsumptionDetails details : consumptionDetails.values()) {
            System.out.println();
            System.out.println("\"VehicleType\": \"" + details.getVehicleType() + "\"");
            System.out.println("\"TotalUnitConsumed\": " + details.getTotalUnitConsumed());
            System.out.println("\"TotalTimeRequired\": " + details.getTotalTimeRequired());
            System.out.println("\"NumberOfTrips Finished\": " + details.getNumberOfTripsFinished());
        }
    }

    public static void printTotalChargingStationTime(HashMap<String, Long> totalChargingStationTime) {
        System.out.println("\nTotalChargingStationTime");
        for (Map.Entry<String, Long> entry : totalChargingStationTime.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

