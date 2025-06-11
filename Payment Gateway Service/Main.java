
public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        try {
            processor.processPayment("tx01",new CreditCardPayment(), 1000000.00);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            processor.processPayment("tx02", new PayPalPayment(), 230.23);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        processor.verifyPayment("tx01");
        processor.verifyPayment("tx02");

        System.out.println(TransactionStatus.FAILED);
    }
}