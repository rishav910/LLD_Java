import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingService {
    private List<ParkingLevel> parkingLevels = new ArrayList<>();
    private static ParkingService obj;
    private static Lock lock = new ReentrantLock();

    private ParkingService () {}
    private ParkingService (ParkingService other) {}

    // Service - Singleton using Lock
    public static ParkingService getInstance () {
        lock.lock();
        try {
            if (obj == null) {
                obj = new ParkingService();
            }
            return obj;
        } catch (Exception e) {
            throw e;
        } finally {
            lock.unlock();
        }
    }

    private VehicleType getVehicleEnum (Vehicle vehicle) {
        if(vehicle.getType().equals("motorcycle")) return VehicleType.MotorCycle;
        else if(vehicle.getType().equals("car")) return VehicleType.Car;
        return VehicleType.Bus;
    }

    // Only admin access
    public void addParkingLevel (ParkingLevel level) {
        parkingLevels.add(level);
    }

    // Only admin access
    public void removeParkingLevel (int levelIndex) {
        parkingLevels.remove(levelIndex);
    }

    public synchronized boolean canPark (Vehicle vehicle) {
        VehicleType type = getVehicleEnum(vehicle);
        for (ParkingLevel level: parkingLevels) {
            if (level.isAvailableSpot(type)) {
                return true;
            }
        }
        return false;
    }

    // Returns spotIndex
    public synchronized int parkMyVehicle (Vehicle vehicle) {
        if (!canPark(vehicle)) {
            return -1;
        }
        VehicleType type = getVehicleEnum(vehicle);
        int spotIdx = 0;
        for (ParkingLevel level: parkingLevels) {
            if (level.isAvailableSpot(type)) {
                return spotIdx + level.fillSpot(type);
            }
            spotIdx += level.getCapacity();
        }
        return -1;
    }

    public long exitVehicle (int spotIdx) {
        if (spotIdx <= 0) {
            return -1;
        }
        for (ParkingLevel level: parkingLevels) {
            if (spotIdx > level.getCapacity())
                spotIdx -= level.getCapacity();
            else
                return level.removeFromSpot(spotIdx);
        }
        return -1;
    }
}
