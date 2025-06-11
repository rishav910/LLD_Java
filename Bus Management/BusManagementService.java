import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusManagementService {
    private final List<Bus> busOptions = new ArrayList<>();
    private static BusManagementService service;
    private static Lock lock = new ReentrantLock(); // If needed

    private BusManagementService () {
        busOptions.add(new SuperCityBUS(10));
        busOptions.add(new NightSuperBUS(8));
    }

    private BusManagementService (BusManagementService other) {}

    public static synchronized BusManagementService getInstance() {
        if (service == null) {
            service = new BusManagementService();
        }
        return service;
    }

    public List<Bus> getAllBusOptions () {
        return busOptions;
    }

    public Bus getBusForStop (String origin, String destination) throws Exception {
        // If origin appears first, destination next - then we can return that bus
        Bus optimalBus = null;
        int prevDistance = -1;
        for (Bus bus: busOptions) {
            if ( bus.canTravel(origin, destination) ) {
                if (optimalBus == null) {
                    optimalBus = bus;
                    prevDistance = bus.distance(origin, destination);
                } else if (prevDistance > bus.distance(origin, destination)) {
                    optimalBus = bus;
                    prevDistance = bus.distance(origin, destination);
                }
            }
        }
        if (optimalBus == null) {
            throw new Exception("No bus found");
        }
        return optimalBus;
    }

    // Update get tickets based on how many passengers boarded in that stop
    public int getTickets (Bus bus, String stop) {
        return bus.getTicketsAvailable(stop);
    }
}
