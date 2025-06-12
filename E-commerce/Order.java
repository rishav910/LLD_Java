import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Order {
    private static int orderID = 0;
    private Map<Product, Integer> orderedItems;
    private double totalPrice;

    public Order (Map<Product, Integer> o) {
        orderedItems = o;
        for (Product p: orderedItems.keySet()) {
            int count = orderedItems.get(p);
            totalPrice += p.getPrice()*count;
        }
        orderID++;
    }

    public void processOrder () {
        Transaction txn = new Transaction(orderID, totalPrice);
        if (!txn.processPayment()) {
            throw new RuntimeException("Payment failed");
        }
        System.out.println("Payment successful");
    }
}
