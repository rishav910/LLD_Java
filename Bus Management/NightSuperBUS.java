import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class NightSuperBUS implements Bus {
    private Set<String> busStops = ConcurrentHashMap.newKeySet();
    private String startingPoint;
    private int ticketsAvailable;
    private String[] stops;
    private final Map<String, Integer> passengerRemoved = new ConcurrentHashMap<>();
    private final Map<String, Integer> passengersAdded = new ConcurrentHashMap<>();

    public NightSuperBUS (int tickets) {
        startingPoint = "A";
        busStops.add("B");busStops.add("F");
        busStops.add("G");
        stops = new String[4];
        stops[0] = "A"; stops[1] = "B"; stops[2] = "F"; stops[3] = "G";
        ticketsAvailable = tickets;
        passengersAdded.put("A", 2); passengersAdded.put("B", 3); passengersAdded.put("F", 4);
        passengerRemoved.put("B", 1); passengerRemoved.put("F", 1); passengerRemoved.put("G", 1);
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
        return "NightSuperBUS";
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
            ticketsAtStop -= passengersAdded.getOrDefault(stop, 0) - passengerRemoved.getOrDefault(stop, 0);
            if(stops[i].equals(stop)) {
                return ticketsAtStop;
            }
        }
        return 0;
    }
}
