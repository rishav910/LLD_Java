import java.text.NumberFormat;
import java.util.Scanner;

public class VendingMachine {
    private Inventory inventory;
    private State state;
    private static int txnID;

    public VendingMachine (Inventory inventory) {
        this.inventory = inventory;
        this.state = State.READY;
        this.txnID = 1;
    }

    public synchronized boolean getItem (Product p, int count) {
        if (!inventory.fetchItem(p, count))
            return false;

        if (!getCash(p, count)) return false;

        this.state = State.DISPENSING_ITEM;
        System.out.println("Dispensing " + p.getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Collect " + p.getName());
        this.state = State.READY;
        return true;
    }

    private boolean getCash (Product p, int count) {
        Transaction txn = new Transaction (txnID, p, count);
        txnID++;
        int remainingCash = txn.doTransaction();
        if(remainingCash < 0) {
            System.out.println("Transaction unsuccessful due to low cash entered");
            int refundAmount = p.getPrice()*count + remainingCash;
            if (refundAmount > 0) {
                System.out.println("Refunding " + NumberFormat.getCurrencyInstance().format(refundAmount));
                this.state = State.REFUND;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Refund successful");
                this.state = State.READY;
            }
            return false;
        }
        else if (remainingCash > 0) {
            System.out.println("Transaction successful");
            System.out.println("Refunding extra cash " + NumberFormat.getCurrencyInstance().format(remainingCash));
            this.state = State.REFUND;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Refund of extra amount successful");
            this.state = State.READY;
            return true;
        }
        System.out.println("Transaction successful");
        return true;
    }

    public int getItemCount (Product p) {
        return inventory.getCount(p);
    }
}
