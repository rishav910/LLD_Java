import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class SuperCityBUS implements Bus {
    private Set<String> busStops = ConcurrentHashMap.newKeySet();
    private String startingPoint;
    private int ticketsAvailable;
    private String[] stops;
    private final Map<String, Integer> passengerRemoved = new ConcurrentHashMap<>();
    private final Map<String, Integer> passengersAdded = new ConcurrentHashMap<>();

    public SuperCityBUS (int tickets) {
        startingPoint = "A";
        // Instead of hardcoding bus stops, can get them from BusService
        busStops.add("B");busStops.add("C");
        busStops.add("D");busStops.add("E");
        busStops.add("F");busStops.add("G");
        ticketsAvailable = tickets;
        stops = new String[7];
        stops[0] = "A"; stops[1] = "B"; stops[2] = "C"; stops[3] = "D";
        stops[4] = "E"; stops[5] = "F"; stops[6] = "G";
        // Instead of hardcoding passenger Count, can get them from BusService
        passengersAdded.put("A", 2); passengersAdded.put("B", 3); passengersAdded.put("C", 4);
        passengerRemoved.put("E", 1); passengerRemoved.put("F", 1); passengerRemoved.put("C", 1);
    }

    @Override
    public Set<String> getBusStops() {
        return busStops;
    }

    @Override
    public String startingPoint() {
        return startingPoint;
    }

    @Override
    public boolean isStop(String stopName) {
        if (busStops.contains(stopName)) {
            return true;
        }
        return false;
    }

    @Override
    public String getBusName() {
        return "SuperCityBUS";
    }

    @Override
    public boolean canTravel (String source, String destination) {
        if (distance(source, destination) != -1) {
            return true;
        }
        return false;
    }

    @Override
    public int distance (String source, String destination) {
        int s = -1;
        boolean originFound = false;
        for (int i=0; i< stops.length; i++) {
            if (!originFound && stops[i].equals(source)) {
                s = i;
                originFound = true;
            } else if(originFound && stops[i].equals(destination)) {
                return i-s;
            }
        }
        return -1;
    }

    @Override
    public int getTicketsAvailable (String stop) {
        if (!busStops.contains(stop)) {
            System.out.println("Tickets not available for this stop");
            return -1;
        }
        int ticketsAtStop = ticketsAvailable;
        for (int i=0; i<stops.length; i++) {
            if(stops[i].equals(stop)) {
                return ticketsAtStop;
            } else {
                ticketsAtStop-=passengersAdded.getOrDefault(stop, 0) - passengerRemoved.getOrDefault(stop, 0);
            }
        }
        return 0;
    }
}
