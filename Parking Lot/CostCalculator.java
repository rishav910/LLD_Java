public class CostCalculator {
    public final int MOTOR_COST_SEC = 1;
    public final int CAR_COST_SEC = 2;
    public final int BUS_COST_SEC = 3;

    public double costToPark (long durationMS, Vehicle vehicle) {
        double durationSec = (double)durationMS/1000;
        if (vehicle.getType().equals("motorcycle")) {
            return durationSec * MOTOR_COST_SEC;
        } else if (vehicle.getType().equals("car")) {
            return durationSec * CAR_COST_SEC;
        } else {
            return durationSec * BUS_COST_SEC;
        }
    }
}
