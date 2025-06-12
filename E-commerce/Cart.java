import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {
    private final Map<Product, Integer> addedProducts = new ConcurrentHashMap<>();

    public void addProduct (Product p) {
        addedProducts.put(p, addedProducts.getOrDefault(p, 0)+1);
    }

    public void showItems () {
        for (Product p: addedProducts.keySet()) {
            int count = addedProducts.get(p);
            System.out.println(p.getName() + ": " + count + " items");
        }
    }

    public boolean orderItems () {
        // Give it to 'Order' class to manage payments
        Order order = new Order(addedProducts);
        order.processOrder();
        addedProducts.clear();
        return true;
    }

    public Map<Product, Integer> getAddedProductMap () {
        return addedProducts;
    }
}
