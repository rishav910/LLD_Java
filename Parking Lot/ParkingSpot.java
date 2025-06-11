public class ParkingSpot {
    private VehicleStatus status;
    private final VehicleType type;
    private long time, elapsedTime;


    public ParkingSpot (VehicleType type) {
        this.type = type;
        this.status = VehicleStatus.NOT_PARKED;
    }

    public void setParkingStatus (VehicleStatus status) {
        this.status = status;
        if (status == VehicleStatus.PARKED) {
            time = System.currentTimeMillis();
        } else if (status == VehicleStatus.NOT_PARKED){
            elapsedTime = System.currentTimeMillis() - time;
        }
    }

    public VehicleStatus getParkingStatus() {
        return status;
    }

    public VehicleType getVehicleType () {
        return this.type;
    }

    public long getTimeParked () {
        return elapsedTime;
    }
}
