
public class Main {
    public static void main(String[] args) {
        RateLimiterService service = RateLimiterService.getInstance();

        service.registerUser("user_1", "sliding_window", 3, 5);
        service.registerUser("user_2", "token_bucket", 5, 10);
        service.registerUser("user_3", "leaky_bucket", 3, 4);

        for (int i = 0; i < 7; i++) {
            System.out.println("User 1 Request " + (i + 1) + " : " + service.allowRequest("user_1"));
            System.out.println("User 2 Request " + (i + 1) + " : " + service.allowRequest("user_2"));
            System.out.println("User 3 Request " + (i + 1) + " : " + service.allowRequest("user_3"));

            try {
                // Put 'main' thread to sleep for 1 second, to test the algorithms
                System.out.println(Thread.currentThread().getName().toUpperCase() + " thread");
                Thread.sleep(1000);
            } catch (InterruptedException e)  {
                System.out.println(e.getMessage());
            }
            System.out.println(Thread.activeCount());
        }
    }
}