import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) {
        // Parking Lot LLD
        // Multiple levels: Each with fixed number of spots
        // Slots categorized as: Motor (1), Car (2), Bus (2) - per hour prices
        // Process: Vehicle enters a parking lot and assigned a available parking spot.
        //          If not available, can't park
        // Exit process: System should calculate the parking fees (vehicle type, duration based)
        // Admin power: Can view slots (free/occupied) - vehicle type wise
        //              Can add/remove slots

        // Entities:
        // 1. Vehicle < MotorCycle, Car, Bus
        // 2. ParkingService
        // 3. ParkingLevel, ParkingSpot
        // 4. Enum - PARKED, NOT_PARKED
        // 5. CostCalculator
        // 6. AdminAccess - User enum {USER, ADMIN}, based on that we can add/remove (for now didn't add)

        var service = ParkingService.getInstance();
        service.addParkingLevel(new ParkingLevel(2, 3, 5));
        service.addParkingLevel(new ParkingLevel(4, 5, 6));
        service.addParkingLevel(new ParkingLevel(1, 1, 1));

        for (int i = 0; i < 11; i++) {
            Vehicle vehicle = new Car();
            if (service.canPark(vehicle)) {
                System.out.println(service.parkMyVehicle(vehicle));
            } else {
                System.out.println("Parking space not available for " + vehicle.getType());
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int spotIdx = 19;
        long timeTaken = service.exitVehicle(spotIdx);
        if (timeTaken == -1) {
            System.out.println("No vehicles parked in " + spotIdx);
        } else {
            System.out.println("Successful exit of vehicle, Time parked: " + ((double)timeTaken/1000) + " seconds");
        }

        // Calculate cost
        CostCalculator a = new CostCalculator();
        double amount = a.costToPark(timeTaken, new Car());
        System.out.println("Cost of parking: " + NumberFormat.getCurrencyInstance().format(amount));
    }
}