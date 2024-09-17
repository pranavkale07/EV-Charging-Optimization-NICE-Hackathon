package com.nice.coday;

import java.io.IOException;
import java.util.*;

public class ElectricityConsumptionCalculatorImpl implements ElectricityConsumptionCalculator {
    @Override
    public ConsumptionResult calculateElectricityAndTimeConsumption(ResourceInfo resourceInfo) throws IOException {

        // Initializing data structures to store input efficiently
        HashMap<String, Integer> entryExitPointInfo = CSVReader.readPointsFromCSV(resourceInfo.getEntryExitPointInfoPath());
        HashMap<String, Integer> chargingStationInfo = CSVReader.readPointsFromCSV(resourceInfo.getChargingStationInfoPath());
        HashMap<String, VehicleInfo> vehicleTypeInfo = CSVReader.loadVehicleTypeInfo(resourceInfo.getVehicleTypeInfoPath());
        HashMap<String, HashMap<String, Long>> timeToChargeVehicleInfo = CSVReader.loadTimeToChargeVehicleInfo(resourceInfo.getTimeToChargeVehicleInfoPath());
        HashMap<Integer, TripInfo> tripDetailsInfo = CSVReader.loadTripDetailsInfo(resourceInfo.getTripDetailsPath());

        // Initialize data structures for results
        HashMap<String, ConsumptionDetails> consumptionDetails = new HashMap<>();
        HashMap<String, Long> totalChargingStationTime = new HashMap<>();

        // Print data structures to verify input
//        DataPrinter.printAllDataStructures(entryExitPointInfo, chargingStationInfo, vehicleTypeInfo, timeToChargeVehicleInfo, tripDetailsInfo);

        // Processing each trip
        for (Map.Entry<Integer, TripInfo> entry : tripDetailsInfo.entrySet()) {
            processTrip(entry, entryExitPointInfo, chargingStationInfo, vehicleTypeInfo, timeToChargeVehicleInfo, consumptionDetails, totalChargingStationTime);
        }

//        DataPrinter.printConsumptionDetails(consumptionDetails);
//        DataPrinter.printTotalChargingStationTime(totalChargingStationTime);

        // Create and return ConsumptionResult containing ConsumptionDetails and TotalChargingStationTime
        ConsumptionResult result = new ConsumptionResult();
        result.setConsumptionDetails(new ArrayList<>(consumptionDetails.values()));
        result.setTotalChargingStationTime(totalChargingStationTime);
        return result;
    }

    private void processTrip(Map.Entry<Integer, TripInfo> entry,
                             HashMap<String, Integer> entryExitPointInfo,
                             HashMap<String, Integer> chargingStationInfo,
                             HashMap<String, VehicleInfo> vehicleTypeInfo,
                             HashMap<String, HashMap<String, Long>> timeToChargeVehicleInfo,
                             HashMap<String, ConsumptionDetails> consumptionDetails,
                             HashMap<String, Long> totalChargingStationTime) {

        //int tripId = entry.getKey(); // Get trip ID
        TripInfo trip = entry.getValue();
        String vehicleType = trip.getVehicleType();
        VehicleInfo vehicle = vehicleTypeInfo.get(vehicleType);

        String currentStart = trip.getEntryPoint();
        double currentStartDist = entryExitPointInfo.get(currentStart);
        String destination = trip.getExitPoint();
        double destinationDist = entryExitPointInfo.get(destination);

        double remainingBatteryPercentage = trip.getRemainingBatteryPercentage();
        double unitsForFullCharge = vehicle.getNumberOfUnitsForFullyCharge();
        double mileage = vehicle.getMileage();

        // calculate the distance the vehicle can travel with the current battery
        double distanceCanTravel = (remainingBatteryPercentage * mileage) / 100;

        // processing a single trip (as a single vehicle may stop multiple times for charging) taking into consideration all the testcases
        while (true){
            double remainingTripDist = destinationDist - currentStartDist;
            if (remainingTripDist > distanceCanTravel) {
                // Needs to stop at charging station for charging

                double canReachDist = currentStartDist + distanceCanTravel;

                String farthestReachableChargingStation = findFarthestReachableChargingStation(currentStartDist, canReachDist, chargingStationInfo);
                if(farthestReachableChargingStation == null){
                    // cannot further reach any charging station due to insufficient battery
                    // trip unsuccessful
                    break;
                }
                double farthestReachableChargingStationDist = chargingStationInfo.get(farthestReachableChargingStation);

                double distanceTravelled = farthestReachableChargingStationDist - currentStartDist;

                double batteryConsumed = (100 * distanceTravelled) / mileage;
                remainingBatteryPercentage = remainingBatteryPercentage - batteryConsumed;

                double unitsToCharge = (unitsForFullCharge * (100 - remainingBatteryPercentage)) / 100;
                Long timeToCharge = (long) (unitsToCharge * timeToChargeVehicleInfo.get(vehicleType).get(farthestReachableChargingStation));

                // Accumulate charging time in totalChargingStationTime (for ConsumptionResult)
                totalChargingStationTime.merge(farthestReachableChargingStation, timeToCharge, Long::sum);

                // Add or update vehicle consumption details
                ConsumptionDetails details = consumptionDetails.getOrDefault(vehicleType, new ConsumptionDetails(vehicleType, 0.0, 0L, 0L));
                details.setTotalUnitConsumed(details.getTotalUnitConsumed() + unitsToCharge);
                details.setTotalTimeRequired(details.getTotalTimeRequired() + timeToCharge);
                consumptionDetails.put(vehicleType, details);

                remainingBatteryPercentage = 100;   // vehicle always leaves charging station with full charge
                currentStartDist = farthestReachableChargingStationDist;    // remainingTripDist will be recalculated based on this
                distanceCanTravel = mileage;

            } else {
                // No charging needed, vehicle can complete trip directly
                // here we only need to increment the no of trips completed by the vehicle
                ConsumptionDetails details = consumptionDetails.getOrDefault(vehicleType, new ConsumptionDetails(vehicleType, 0.0, 0L, 0L));
                details.setNumberOfTripsFinished(details.getNumberOfTripsFinished() + 1);
                consumptionDetails.put(vehicleType, details);
                break;
            }
        }
    }

    private String findFarthestReachableChargingStation(double currDist, double canReach, HashMap<String, Integer> chargingStationInfo) {
        String farthestChargingStation = null;
        int maxDistance = Integer.MIN_VALUE;

        // Iterate over all charging stations
        for (Map.Entry<String, Integer> entry : chargingStationInfo.entrySet()) {
            String station = entry.getKey();
            int stationDistance = entry.getValue();

            // Check if the station distance is within the reachable distance and is farther than the current farthest
            if (stationDistance > currDist && stationDistance <= canReach && stationDistance > maxDistance) {
                maxDistance = stationDistance;
                farthestChargingStation = station;
            }
        }
        return farthestChargingStation;
    }
}