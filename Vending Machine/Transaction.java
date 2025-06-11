import java.text.NumberFormat;
import java.util.Scanner;

public class Transaction {
    private final int txnID;
    private final Product product;
    private final int count;
    private final int totalPrice;

    public Transaction(int txnID, Product p, int count) {
        this.txnID = txnID;
        this.product = p;
        this.count = count;
        this.totalPrice = p.getPrice()*count;
    }

    public int doTransaction () {
        System.out.println("Please enter cash of " + NumberFormat.getCurrencyInstance().format(totalPrice) + "!!");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Cash entered: ");
        int cashEntered = scanner.nextInt();
        System.out.println("Transaction in progress...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return cashEntered-totalPrice;
    }
}
