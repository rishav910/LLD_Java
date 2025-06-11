import java.text.NumberFormat;

public class CreditCardPayment implements Payment {
    @Override
    public boolean initiatePayment(double amount) {
        // Will call CreditCard client
        try {
            if(amount > 100000) {
                System.out.println("Exceeded limit of: " + NumberFormat.getCurrencyInstance().format(100000.87));
                throw new InterruptedException();
            }
            System.out.println("Credit card payment of: " + NumberFormat.getCurrencyInstance().format(amount) + " done");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean verifyPayment () {
        System.out.println("Credit card payment verified");
        return true;
    }

    // Can override RefundPayment() logic if needed
}
