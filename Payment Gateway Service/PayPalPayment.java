import java.text.NumberFormat;

public class PayPalPayment implements Payment {
    @Override
    public boolean initiatePayment(double amount) {
        try {
            // Call paypal client to start payment
            System.out.println("Paypal payment of: " + NumberFormat.getCurrencyInstance().format(amount) + " done");
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    @Override
    public boolean verifyPayment () {
        System.out.println("Paypal payment verified");
        return true;
    }
}
