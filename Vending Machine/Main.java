import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Vending machine:
        // Keep track of inventory
        // Person should be able to insert cash and select and item
        // Machine should confirm insert cash amount, price of selected item
        // Machine will display error in case of insufficient cash, unavailable item
        // User gets the item if all above cases are fine\
        // While machine is dispensing an item - Insert cash (X), Another txn (X) - Concurrency

        // Entities:
        // 1. VendingMachine - handles display
        // 2. Inventory
        // 3. Product - getPrice(), getName()
        // 4. Transaction - refund
        // 5. Enum State {READY, COLLECT_CHANGE, DISPENSING_ITEM}

        var p1 = new Product("Coke", 20);
        var p2 = new Product("Lays", 10);
        var p3 = new Product("Fanta", 40);
        Map<Product, Integer> mp = new HashMap<>();
        mp.put(p1, 2);
        mp.put(p2, 3);
        mp.put(p3, 1);

        Inventory inventory = new Inventory(mp);

        var machine = new VendingMachine(inventory);

        machine.getItem(p1, 1);
        machine.getItem(p2, 2);
        machine.getItem(p2, 1);
        var count = machine.getItemCount(p2);
        System.out.println(p2.getName() + " has " + count + " items left");
    }
}