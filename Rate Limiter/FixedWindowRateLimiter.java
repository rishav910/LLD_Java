public class FixedWindowRateLimiter implements RateLimiter {
    private final int capacity;
    private final long windowSizeMS;
    private int requestCount;
    private long windowStart;

    public FixedWindowRateLimiter (int maxRequests, long windowSizeMS) {
        this.capacity = maxRequests;
        this.windowSizeMS = windowSizeMS;
        this.windowStart = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - windowStart >= windowSizeMS) {
            requestCount = 0;
            windowStart = currentTime;
        }
        if (requestCount < capacity) {
            requestCount++;
            return true;
        }
        return false;
    }
}
