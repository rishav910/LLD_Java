public class RetryPolicy {
    private static final int MAX_RETRIES = 3;

    // This method will try MAX_RETRIES attempts to pay (using 5 secs between payment) - Thread.sleep
    public boolean retryPayment (Payment payment, double amount) {
        int attempts = 1;
        while (attempts < MAX_RETRIES) {
            try {
                if (payment.initiatePayment(amount))
                    return true;
                else throw new InterruptedException();
            } catch (Exception e) {
                // Thread sleep
                try {
                    System.out.println("Retrying payment " + (attempts+1));
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                attempts++;
            }
        }
        return false;
    }
}
