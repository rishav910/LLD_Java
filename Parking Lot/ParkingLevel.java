import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {
    private List<ParkingSpot> currentLevel = new ArrayList<>();
    private int capacity;

    public ParkingLevel (int motorcycleCount, int carCount, int busCount) {
        capacity = motorcycleCount + carCount + busCount;
        while (motorcycleCount > 0) {
            currentLevel.add(new ParkingSpot(VehicleType.MotorCycle));
            motorcycleCount--;
        }
        while (carCount > 0) {
            currentLevel.add(new ParkingSpot(VehicleType.Car));
            carCount--;
        }
        while (busCount > 0) {
            currentLevel.add(new ParkingSpot(VehicleType.Bus));
            busCount--;
        }
    }

    public synchronized boolean isAvailableSpot (VehicleType type) {
        for (ParkingSpot spot: currentLevel) {
            if (spot.getVehicleType() == type && spot.getParkingStatus() == VehicleStatus.NOT_PARKED) {
                return true;
            }
        }
        return false;
    }

    public synchronized int fillSpot (VehicleType type) {
        int index = 0;
        for (ParkingSpot spot: currentLevel) {
            if (spot.getVehicleType() == type && spot.getParkingStatus() == VehicleStatus.NOT_PARKED) {
                spot.setParkingStatus(VehicleStatus.PARKED);
                return index+1;
            }
            index++;
        }
        return index;
    }

    public synchronized int getCapacity () {
        return capacity;
    }

    public long removeFromSpot (int index) {
        ParkingSpot spot = currentLevel.get(index-1);
        if (spot.getParkingStatus() == VehicleStatus.NOT_PARKED) {
            return -1;
        }
        spot.setParkingStatus(VehicleStatus.NOT_PARKED);
        return spot.getTimeParked();
    }
}
