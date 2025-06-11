public interface Payment {
    public boolean initiatePayment (double amount);
    public boolean verifyPayment();

    // Can add refundPayment() logic (if needed)
}
