
public class Main {
    public static void main(String[] args) {
        // Entities: Bus < SuperCityBUS, NightSuperBUS (getBusStops(), startingPoint(), isStop(String) - HashSet of stops)
        // BusManagementService: Singleton, getAllBusOptions(), getBusForStop(String), getTickets(String)
        // Available tickets - passengerAdded - passengerRemoved Logic

        // Improvement: Can also add 'City' class to track stops of buses.
        // It can have a method: checkDistance(otherCity)
        // We can pass List<City> to the add it to Bus stop list.

        var busService = BusManagementService.getInstance();
        Bus type = null;
        String stop = "B";
        try {
            type = busService.getBusForStop("A", "G");
        } catch (Exception e) {
            e.getMessage();
        }

        // Get bus tickets - for a bus stop
        if (type != null)
            System.out.println("Tickets available at stop " + stop + " for " + type.getBusName() + " : " + type.getTicketsAvailable(stop));

    }
}