
public class TokenBucketRateLimiter implements RateLimiter{
    private final int capacity;
    private final double refillRateMS;
    private int tokens;
    private long lastRefillTimestamp;

    public TokenBucketRateLimiter (int maxRequests, double refillRateMS) {
        this.capacity = maxRequests;
        this.refillRateMS = refillRateMS; // in ms -> (double)maxRequests/windowSizeMS
        this.tokens = capacity;
        this.lastRefillTimestamp = -1;
    }

    // Thread safe - synchronized method
    @Override
    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis(); // number of ms since midnight, January 1, 1970 UTC
        // Initialize last timestamp (if absent)
        if (lastRefillTimestamp == -1) {
            lastRefillTimestamp = currentTime;
        }

        // Update tokens according to time elapsed
        long elapsedTime = (currentTime - lastRefillTimestamp); // in ms

        if (elapsedTime > 0 ) {
            int newTokens = Math.min(capacity, tokens + (int)(refillRateMS * elapsedTime));
            tokens = newTokens;
            lastRefillTimestamp = currentTime;
        }

        // Updated tokens if > 0, return true, store token--
        if(tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }
}
