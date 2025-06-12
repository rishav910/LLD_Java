import java.text.NumberFormat;
import java.util.Scanner;

public class Transaction {
    private final int orderID;
    private final double amount;

    public Transaction(int orderID, double amount) {
        this.orderID = orderID;
        this.amount = amount;
    }

    public boolean processPayment () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please pay " + NumberFormat.getCurrencyInstance().format(amount));
        System.out.print("Enter amount: ");
        double receivedAmount = scanner.nextDouble();
        if (receivedAmount < amount) {
            System.out.println("Unsuccessful payment of " + NumberFormat.getCurrencyInstance().format(amount));
            return false;
        }
        return true;
    }
}
