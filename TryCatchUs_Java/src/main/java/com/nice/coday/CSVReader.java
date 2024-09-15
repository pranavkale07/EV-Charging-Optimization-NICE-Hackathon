package com.nice.coday;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class CSVReader {

    // Load EntryExitPointInfo and ChargingStationInfo into HashMap<String, Integer>
    public static HashMap<String, Integer> readPointsFromCSV(Path filePath) throws IOException {
        HashMap<String, Integer> pointsInfo = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                pointsInfo.put(values[0], Integer.parseInt(values[1]));
            }
        }
        return pointsInfo;
    }

    // Load VehicleTypeInfo into HashMap<String, VehicleInfo>
    public static HashMap<String, VehicleInfo> loadVehicleTypeInfo(Path filePath) throws IOException{
        HashMap<String, VehicleInfo> vehicleTypeInfo = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String vehicleType = values[0];
                int numberOfUnitsForFullyCharge = Integer.parseInt(values[1]);
                int mileage = Integer.parseInt(values[2]);
                vehicleTypeInfo.put(vehicleType, new VehicleInfo(vehicleType, numberOfUnitsForFullyCharge, mileage));
            }
        }
        return vehicleTypeInfo;
    }

    // Load TimeToChargeVehicleInfo into HashMap<String, HashMap<String, Long>>
    public static HashMap<String, HashMap<String, Long>> loadTimeToChargeVehicleInfo(Path filePath) throws IOException{
        HashMap<String, HashMap<String, Long>> timeToChargeVehicleInfo = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String vehicleType = values[0];
                String chargingStation = values[1];
                long timeToChargePerUnit = Long.parseLong(values[2]);

                // Nested map for vehicleType -> chargingStation -> timeToChargePerUnit
                timeToChargeVehicleInfo
                        .computeIfAbsent(vehicleType, k -> new HashMap<>())
                        .put(chargingStation, timeToChargePerUnit);
            }
        }
        return timeToChargeVehicleInfo;
    }

    // Load TripDetailsInfo into HashMap<String, TripObject>
    public static HashMap<Integer, TripInfo> loadTripDetailsInfo(Path filePath) throws IOException{
        HashMap<Integer, TripInfo> tripDetailsInfo = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String vehicleType = values[1];
                int remainingBatteryPercentage = Integer.parseInt(values[2]);
                String entryPoint = values[3];
                String exitPoint = values[4];

                tripDetailsInfo.put(id, new TripInfo(id, vehicleType, remainingBatteryPercentage, entryPoint, exitPoint));
            }
        }
        return tripDetailsInfo;
    }

}
