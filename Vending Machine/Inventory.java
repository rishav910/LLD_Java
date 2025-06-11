import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private final Map<Product, Integer> products = new ConcurrentHashMap<>();

    public Inventory (Map<Product, Integer> products) {
        for(Product p: products.keySet()) {
            int cnt = products.get(p);
            this.products.put(p, cnt);
        }
    }

    public boolean fetchItem (Product p, int count) {
        if (!products.containsKey(p)) {
            System.out.println("Product not available");
            return false;
        }

        int available = products.get(p);
        if (count > available) {
            System.out.println(p.getName() + " requested has only " + available + " items left");
           return false;
        } else if( count < available) {
            products.put(p, available -count);
        } else {
            products.remove(p);
        }
        return true;
    }

    public int getCount (Product p) {
        if (!products.containsKey(p)) {
            return 0;
        }
        return products.get(p);
    }

}
