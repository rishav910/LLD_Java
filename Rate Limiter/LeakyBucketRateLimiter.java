import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LeakyBucketRateLimiter implements RateLimiter{
    private final int capacity;
    private final long leakRateMS;
    private final Queue<Long> bucket = new ConcurrentLinkedQueue<>();

    public LeakyBucketRateLimiter (int maxRequests, long leakRateMS) {
        this.capacity = maxRequests;
        this.leakRateMS = leakRateMS; // currently in ms
        // Thread = Should eliminate leaks at a constant rate irrespective of requests
        Thread t = new Thread( () -> { // Runnable f-interface implementation using lambda
           while (true) {
               leakRequests();
               try {
                    Thread.sleep(leakRateMS); // This will leak requests every 'leakRateMS' ms
               } catch (InterruptedException e) {
                   System.out.println("Break");
                   break;
               }
           }
        });
        // Assigns 't' as low-priority thread, this will close the thread once 'main' thread is closed
        t.setDaemon(true); // Daemon thread doesn't block JVM shutdown (main thread shutdown)
        t.start();
    }

    private synchronized void leakRequests () {
        if (bucket.size() > 0) {
            bucket.remove();
//            System.out.println("Leaked 1 token");
        }
    }

    // Thread safe - synchronized keyword
    @Override
    public synchronized boolean allowRequest() {
        // Removal of timestamps are handled by leakRequests() thread

        long currentTime = System.currentTimeMillis();
        if (bucket.size() < capacity) {
            bucket.add(currentTime);
            return true;
        }
        return false;
    }

}
