import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private final Map<Product, Integer> products = new ConcurrentHashMap<>();
    private static Inventory obj;
    private Inventory () {}

    public synchronized static Inventory getInstance () {
        if (obj == null) {
            obj = new Inventory();
        }
        return obj;
    }

    public void addProduct (Product p, int cnt, User user) {
        if (user.getRole() == Role.ADMIN) {
            products.put(p, cnt);
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

    public void showItems () {
        for (Product p: products.keySet()) {
            int count = products.get(p);
            System.out.println(p.getName() + ": " + count + " items");
        }
    }

    public void reduceItems (Cart cart) {
        Map<Product, Integer> mp = cart.getAddedProductMap();
        for (Product p: mp.keySet()) {
            int cnt = mp.get(p);
            products.put(p, products.get(p)-cnt);
            if (products.get(p) == 0) {
                products.remove(p);
            }
        }
    }

}
