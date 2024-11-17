# Electric Vehicle Charging Optimization on Green Corridors  

This project addresses the optimization of electricity consumption and charging infrastructure for electric vehicles (EVs) traveling along a green corridor from Kashmir to Kanyakumari. It calculates daily aggregate electricity consumption, charging times, and station utilization to ensure effective infrastructure management and a smooth travel experience for EV users.  

## Problem Statement  

**NICE Power Ltd**, in partnership with the Government of India, aims to make the Kashmir-Kanyakumari highway a zero-emission green corridor by allowing only EVs. With a growing number of EVs using the highway daily, efficient charging station management and accurate energy usage calculations are critical.  

This project seeks to:  
1. Compute the total electricity consumption and charging time for all EVs.  
2. Identify the charging station load and usage patterns.  
3. Help optimize the infrastructure to meet daily demand efficiently.  

### Key Features  

- Calculate total energy consumption for various EV types based on their battery specifications and mileage.  
- Estimate the total time spent charging vehicles, both by type and at specific stations.  
- Track and report trip completion metrics.  
- Ensure optimal use of charging stations based on vehicle travel capacities.  

## Input Data  

This solution processes the following input files to generate results:  

1. **`ChargingStationInfo.csv`:**  
   - Details of charging stations and their distances from the start of the highway.  

2. **`EntryExitPointInfo.csv`:**  
   - List of entry and exit points along the highway with their distances.  

3. **`VehicleTypeInfo.csv`:**  
   - Information about vehicle specifications, including battery capacity, mileage, and energy consumption rates.  

4. **`TimeToChargeVehicleInfo.csv`:**  
   - Charging time required per unit of electricity for each vehicle type at various stations.  

5. **`TripDetails.csv`:**  
   - Trip-specific data, including vehicle type, initial battery levels, and travel start and end points.  

## Outputs  

The project generates outputs in JSON format:  

1. **Vehicle-wise Consumption Summary:**  
   - **Vehicle Type:** Type of the EV.  
   - **Total Energy Consumed:** Aggregate energy used by the vehicle type.  
   - **Total Charging Time:** Time spent at charging stations for each vehicle type.  
   - **Completed Trips:** Total number of trips successfully finished by each vehicle type.  

2. **Charging Station Utilization:**  
   - Total time spent charging at each station, providing insights into station usage patterns.  

### Example Output  

```json
{
  "vehicleTypeSummary": [
    {
      "vehicleType": "V1",
      "totalEnergyConsumed": 500,
      "totalChargingTime": 72000,
      "completedTrips": 15
    },
    {
      "vehicleType": "V2",
      "totalEnergyConsumed": 450,
      "totalChargingTime": 68000,
      "completedTrips": 10
    }
  ],
  "chargingStationSummary": {
    "C1": 36000,
    "C2": 32000
  }
}
