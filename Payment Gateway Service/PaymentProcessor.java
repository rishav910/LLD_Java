import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentProcessor {
    Map<String, Transaction> uniqueTransactions = new ConcurrentHashMap<>();

    public void processPayment (String uniqueKey, Payment method, double amount) {
        if (uniqueTransactions.containsKey(uniqueKey)) {
            System.out.println("Transaction already processed");
            return;
        }

        Transaction txn = new Transaction(uniqueKey, amount);
        if (method.initiatePayment(amount)) {
            txn.setStatus(TransactionStatus.SUCCESS);
        } else {
            RetryPolicy retry = new RetryPolicy();
            retry.retryPayment(method, amount);
            txn.setStatus(TransactionStatus.FAILED);
        }
        uniqueTransactions.put(uniqueKey, txn);
    }

    public void verifyPayment (String uniqueKey) {
        if (!uniqueTransactions.containsKey(uniqueKey)) {
            System.out.println("Transaction ID does not exist");
            return;
        } else {
            Transaction txn = uniqueTransactions.get(uniqueKey);
            if(txn.getStatus() == TransactionStatus.SUCCESS) {
                System.out.println("Successful payment");
            } else if(txn.getStatus() == TransactionStatus.FAILED) {
                System.out.println("Failed payment");
            } else {
                System.out.println("Initiated payment");
            }
        }
    }
}
