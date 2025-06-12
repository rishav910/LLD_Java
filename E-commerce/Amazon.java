import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Amazon {
    private Cart cart;
    private Inventory inventory;

    public Amazon () {
        cart = new Cart();
        inventory = Inventory.getInstance();
    }

    // Show products
    public void showProducts () {
        inventory.showItems();
    }

    // Add to cart
    public void addToCart (Product p, int cnt) {
        if (inventory.fetchItem(p, cnt)) {
            while (cnt > 0) {
                cart.addProduct(p);
                cnt--;
            }
            return;
        }
    }

    // Show items in cart
    public void showItemsInCart () {
        cart.showItems();
    }

    // Order from cart
    public void orderFromCart () {
        if (!cart.orderItems()) {
            System.out.println("Unable to order from cart");
            return;
        }
        inventory.reduceItems(cart);
    }
}
