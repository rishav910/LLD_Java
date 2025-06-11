import java.util.Date;

public class Transaction {
    private String transactionID;
    private double amount;
    private TransactionStatus status;
    private Date createdAt;

    public Transaction(String transactionID, double amount) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.status = TransactionStatus.INITIATED; // Class with static property
        this.createdAt = new Date();
    }

    public void setStatus (TransactionStatus status) {
        this.status = status;
    }

    public TransactionStatus getStatus () {
        return this.status;
    }

    public String getTransactionID () {
        return this.transactionID;
    }
}
