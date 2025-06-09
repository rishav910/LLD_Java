import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter {
    private final int capacity;
    private final long windowSizeMS;
    private final Queue<Long> timestamps = new ConcurrentLinkedQueue<>();

    public SlidingWindowRateLimiter (int maxRequests, long windowSizeMS) {
        this.capacity = maxRequests;
        this.windowSizeMS = windowSizeMS;
    }

    @Override
    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        while(timestamps.size() > 0 && (currentTime-timestamps.peek()) >= windowSizeMS) {
            timestamps.remove();
        }

        if(timestamps.size() < capacity) {
            timestamps.add(currentTime);
            return true;
        }

        return false;
    }
}
