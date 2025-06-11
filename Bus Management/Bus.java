import java.util.Set;

public interface Bus {
    public Set<String> getBusStops();
    public String startingPoint();
    public boolean isStop(String stopName);
    public String getBusName();
    public boolean canTravel (String source, String destination);
    public int distance (String source, String destination);
    public int getTicketsAvailable (String stop);

    // Since many methods are being duplicated in Both bus implementations
    // can add an abstract class BusAbstract that implements all common methods
    // Common methods: getTicketsAvailable, distance, canTravel, isStop
    // Then extend this abstract class to write SuperCityBUS & NightSuperBUS
}
