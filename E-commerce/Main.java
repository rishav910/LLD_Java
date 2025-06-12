public class Main {
    public static void main(String[] args) {
        // E-commerce platform:
        // User (phone, email, name) should be able to login, logout
        // Admin should be able to add items to inventory (How?)
        // Items grouped as fruits, vegetables, etc.
        // Cart management - Add/Remove items
        // Users should be able to place orders from their cart
        // Support transaction for payment
        // Notification system to notify users

        // Entities:
        // 1. User
        // 2. AdminService - enum {CUSTOMER, ADMIN}
        // 3. Inventory, Product
        // 4. Cart
        // 5. Order
        // 6. Transaction
        // 7. Notification (do it later)

        var auth = new AuthenticationService();
        auth.registerUser("ADMIN", "admin@amazon.com", "pass1", Role.ADMIN);
        User admin = auth.getUser("admin@amazon.com");
        var store = Inventory.getInstance();
        Product p1 = new Product("Coke", 30);
        Product p2 = new Product("Wall clock", 300);
        Product p3 = new Product("Mobile", 19000);
        Product p4 = new Product("Mobile", 19000);

        // This initially gives false, but p3 and p4 are same. Need to write custom equals() method of Object class
        System.out.println(p3.equals(p4)); // checks name and price fields for equality
        System.out.println(p3.hashCode()); // uses Objects.hash(name, price)
        System.out.println(p4.hashCode());

        store.addProduct(p1, 2, admin);
        store.addProduct(p2, 3, admin);
        store.addProduct(p3, 4, admin);

        auth.registerUser("Rishav Saha", "rishavsaha37@gmail.com", "pass2");
        User rishav = auth.getUser("rishavsaha37@gmail.com");
        auth.loginUser("rishavsaha37@gmail.com", "pass2");
        var amazon1 = auth.openAmazon(rishav);

//        if (amazon1 != null) {
//            System.out.println("Amazon display for " + rishav.getName() + " :");
//            amazon1.showProducts();
//            amazon1.addToCart(p1, 2);
//            amazon1.orderFromCart();
//            System.out.println("\n\n");
//        }

        auth.registerUser("Shweta Saha", "shwetasaha47@gmail.com", "pass3");
        User shweta = auth.getUser("shwetasaha47@gmail.com");
        auth.loginUser("shwetasaha47@gmail.com", "pass3");
        var amazon2 = auth.openAmazon(shweta);

//        if(amazon2 != null) {
//            System.out.println("Amazon display for " + shweta.getName() + " :");
//            amazon2.showProducts();
//            amazon2.addToCart(p1, 2);
//        }
    }
}